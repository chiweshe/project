package com.example.employeemanagement.business.logic.imp;

import com.example.employeemanagement.business.logic.api.PayrollService;
import com.example.employeemanagement.domain.Employee;
import com.example.employeemanagement.domain.EmployeeAllowance;
import com.example.employeemanagement.domain.EmployeeDeduction;
import com.example.employeemanagement.domain.Payroll;
import com.example.employeemanagement.domain.SalaryStructure;
import com.example.employeemanagement.domain.Status;
import com.example.employeemanagement.domain.TaxSlab;
import com.example.employeemanagement.repository.EmployeeAllowanceRepository;
import com.example.employeemanagement.repository.EmployeeDeductionRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.repository.PayrollRepository;
import com.example.employeemanagement.repository.SalaryStructureRepository;
import com.example.employeemanagement.repository.TaxSlabRepository;
import com.example.employeemanagement.utils.dto.PayrollDto;
import com.example.employeemanagement.utils.enums.Messages;
import com.example.employeemanagement.utils.messages.api.MessageService;
import com.example.employeemanagement.utils.requests.CreateBulkPayrollRequest;
import com.example.employeemanagement.utils.requests.CreatePayrollRequest;
import com.example.employeemanagement.utils.responses.PayrollResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class PayrollServiceImpl  implements PayrollService {


    private final SalaryStructureRepository salaryStructureRepository;

    private final EmployeeAllowanceRepository employeeAllowanceRepository;

    private final EmployeeDeductionRepository employeeDeductionRepository;

    private final EmployeeRepository employeeRepository;

    private final PayrollRepository payrollRepository;

    private final TaxSlabRepository taxSlabRepository;

    private final MessageService messageService;

    private final ModelMapper modelMapper;

    public PayrollServiceImpl(SalaryStructureRepository salaryStructureRepository,
                              EmployeeAllowanceRepository employeeAllowanceRepository,
                              EmployeeDeductionRepository employeeDeductionRepository, EmployeeRepository employeeRepository,
                              PayrollRepository payrollRepository, TaxSlabRepository taxSlabRepository, MessageService messageService, ModelMapper modelMapper) {
        this.salaryStructureRepository = salaryStructureRepository;
        this.employeeAllowanceRepository = employeeAllowanceRepository;
        this.employeeDeductionRepository = employeeDeductionRepository;
        this.employeeRepository = employeeRepository;
        this.payrollRepository = payrollRepository;
        this.taxSlabRepository = taxSlabRepository;
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING = RoundingMode.HALF_UP;

    @Override
    public PayrollResponse createPayroll(CreatePayrollRequest createPayrollRequest, Locale locale, String username) {
        String message;

        Optional<Employee> employeeOpt = employeeRepository.findByIdAndStatusNot(
                createPayrollRequest.getEmployeeId(), Status.DELETED);

        if (employeeOpt.isEmpty()) {
            message = messageService.getMessage(Messages.EMPLOYEE_NOT_FOUND.getCode(), new String[]{}, locale);
            return buildResponse(404, false, message, null, null, null);
        }

        Employee employee = employeeOpt.get();

        Optional<Payroll> existingPayroll = payrollRepository.findByEmployeeIdAndPayrollMonth(
                employee.getId(), createPayrollRequest.getPayrollMonth());
        if (existingPayroll.isPresent()) {
            message = messageService.getMessage(Messages.PAYROLL_ALREADY_EXISTS.getCode(), new String[]{}, locale);
            return buildResponse(409, false, message, null, null, null);
        }

        SalaryStructure salaryStructure = salaryStructureRepository
                .findTopByEmployeeIdAndIsActiveTrueAndStatus(employee.getId(), Status.ACTIVE);

        if (salaryStructure == null) {
            message = messageService.getMessage(Messages.SALARY_STRUCTURE_NOT_FOUND.getCode(), new String[]{}, locale);
            return buildResponse(404, false, message, null, null, null);
        }

        List<EmployeeAllowance> allowances = employeeAllowanceRepository.findByEmployeeIdAndStatusNot(
                employee.getId(), Status.DELETED);

        List<EmployeeDeduction> deductions = employeeDeductionRepository.findByEmployeeIdAndStatusNot(
                employee.getId(), Status.DELETED);

        BigDecimal totalAllowances = allowances.stream()
                .map(EmployeeAllowance::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(Optional.ofNullable(salaryStructure.getBonus()).orElse(BigDecimal.ZERO));

        BigDecimal basicSalary = salaryStructure.getBasicSalary();
        BigDecimal grossPay = basicSalary.add(totalAllowances);

        BigDecimal otherDeductions = deductions.stream()
                .map(EmployeeDeduction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        TaxSlab taxSlab = taxSlabRepository
                .findFirstByLowerBoundLessThanEqualAndUpperBoundGreaterThanEqual(grossPay, grossPay)
                .orElse(null);

        BigDecimal monthlyTax = calculateTax(grossPay, taxSlab);
        BigDecimal totalDeductions = otherDeductions.add(monthlyTax);
        BigDecimal netPay = grossPay.subtract(totalDeductions);

        Payroll payroll = new Payroll();
        payroll.setEmployee(employee);
        payroll.setEmployeeName(employee.getFullName());
        payroll.setEmployeeCode(employee.getEmployeeCode());
        payroll.setPayrollMonth(createPayrollRequest.getPayrollMonth());
        payroll.setBasicSalary(basicSalary);
        payroll.setTotalAllowances(totalAllowances);
        payroll.setTotalDeductions(totalDeductions);
        payroll.setGrossPay(grossPay);
        payroll.setNetPay(netPay);
        payroll.setGeneratedAt(LocalDateTime.now());
        payroll.setStatus(Status.ACTIVE);

        Payroll savedPayroll = payrollRepository.save(payroll);

        PayrollDto payrollDto = modelMapper.map(savedPayroll, PayrollDto.class);
        payrollDto.setEmployeeId(employee.getId());
        payrollDto.setEmployeeName(employee.getFullName());
        payrollDto.setTaxAmount(monthlyTax);

        message = messageService.getMessage(Messages.PAYROLL_CREATED_SUCCESSFULLY.getCode(), new String[]{}, locale);
        return buildResponse(201, true, message, payrollDto, null, null);
    }

    @Override
    public PayrollResponse findAllAsPage(Pageable pageable, Locale locale) {
        Page<Payroll> payrollPage = payrollRepository.findAllByStatusNot(Status.DELETED, pageable);

        Page<PayrollDto> payrollDtoPage = payrollPage.map(payroll -> {
            PayrollDto dto = modelMapper.map(payroll, PayrollDto.class);

            if (payroll.getEmployee() != null) {
                dto.setEmployeeId(payroll.getEmployee().getId());
                dto.setEmployeeName(payroll.getEmployee().getFullName());
            }

            return dto;
        });

        String message = messageService.getMessage(Messages.PAYROLL_DETAILS_RETRIEVED_SUCCESSFULLY.getCode(), new String[]{}, locale);
        return buildResponse(200, true, message, null, null, payrollDtoPage);
    }

    @Override
    public PayrollResponse createBulkPayroll(CreateBulkPayrollRequest createBulkPayrollRequest, Locale locale, String username)
    {
        String message;
        List<PayrollDto> processedPayrolls = new ArrayList<>();
        List<String> failedEmployees = new ArrayList<>();

        List<Employee> employees = employeeRepository.findAllByStatusNot(Status.DELETED);

        for (Employee employee : employees) {
            CreatePayrollRequest request = new CreatePayrollRequest();
            request.setEmployeeId(employee.getId());
            request.setPayrollMonth(createBulkPayrollRequest.getPayrollMonth());

            PayrollResponse payrollResponse = createPayroll(request, locale, username);

            if (payrollResponse.isSuccess() && payrollResponse.getPayrollDto() != null) {
                processedPayrolls.add(payrollResponse.getPayrollDto());
            } else {
                failedEmployees.add(employee.getFullName() + " (Employee Code: " + employee.getEmployeeCode() + ")");
            }
        }

        if (!failedEmployees.isEmpty()) {
            message = messageService.getMessage(Messages.BULK_PAYROLL_PARTIAL_SUCCESS.getCode(), new String[]{}, locale)
                    + " Failed for: " + String.join(", ", failedEmployees);
            return buildResponse(200, true, message, null, processedPayrolls, null); // 207 Multi-Status
        }

        message = messageService.getMessage(Messages.BULK_PAYROLL_SUCCESS.getCode(), new String[]{}, locale);
        return buildResponse(200, true, message, null, processedPayrolls, null);
    }

    public PayrollResponse buildResponse(int statusCode, Boolean success, String message,
                                         PayrollDto payrollDto, List<PayrollDto> payrollDtoList,
                                         Page<PayrollDto> payrollDtoPage) {
        PayrollResponse payrollResponse = new PayrollResponse();
        payrollResponse.setStatusCode(statusCode);
        payrollResponse.setSuccess(success);
        payrollResponse.setMessage(message);
        payrollResponse.setPayrollDto(payrollDto);
        payrollResponse.setPayrollDtoList(payrollDtoList);
        payrollResponse.setPayrollDtoPage(payrollDtoPage);


        return payrollResponse;
    }

    private BigDecimal calculateTax(BigDecimal grossPay, TaxSlab taxSlab) {
        if (taxSlab == null || taxSlab.getRate() == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal fixedNonTaxable = Optional.ofNullable(taxSlab.getFixedAmount()).orElse(BigDecimal.ZERO);
        BigDecimal amountToBeTaxed = grossPay.subtract(fixedNonTaxable).max(BigDecimal.ZERO);
        BigDecimal taxRate = taxSlab.getRate().divide(BigDecimal.valueOf(100), 4, ROUNDING);

        return amountToBeTaxed.multiply(taxRate).setScale(SCALE, ROUNDING);
    }
}
