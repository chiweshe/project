package com.example.employeemanagement.business.logic.imp;

import com.example.employeemanagement.business.logic.api.PayrollService;
import com.example.employeemanagement.domain.*;
import com.example.employeemanagement.repository.*;
import com.example.employeemanagement.utils.dto.PayrollDto;
import com.example.employeemanagement.utils.enums.Messages;
import com.example.employeemanagement.utils.messages.api.MessageService;
import com.example.employeemanagement.utils.requests.CreatePayrollRequest;
import com.example.employeemanagement.utils.responses.PayrollResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
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

    @Override
    public PayrollResponse createPayroll(CreatePayrollRequest createPayrollRequest, Locale locale, String username) {
            String message = "";

            Optional<Employee> employee = employeeRepository.findByIdAndStatusNot(createPayrollRequest.getEmployeeId(),
                    Status.DELETED);

            if (employee.isEmpty()) {
                message = messageService.getMessage(Messages.EMPLOYEE_NOT_FOUND.getCode(), new String[]{}, locale);
                return buildResponse(404, false, message, null, null, null);
            }

            SalaryStructure salaryStructure = salaryStructureRepository.findTopByEmployeeIdAndIsActiveTrueAndStatus(createPayrollRequest.getEmployeeId(),
                    Status.ACTIVE);

            if (salaryStructure == null) {
                message = messageService.getMessage(Messages.SALARY_STRUCTURE_NOT_FOUND.getCode(), new String[]{}, locale);
                return buildResponse(404, false, message, null, null, null);
            }

            List<EmployeeAllowance> allowances = employeeAllowanceRepository.findByEmployeeIdAndStatusNot(
                    createPayrollRequest.getEmployeeId(), Status.DELETED);
            List<EmployeeDeduction> deductions = employeeDeductionRepository.findByEmployeeIdAndStatusNot(
                    createPayrollRequest.getEmployeeId(), Status.DELETED);

            BigDecimal totalAllowances = allowances.stream()
                    .map(EmployeeAllowance::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            if (salaryStructure.getBonus() != null) {
                totalAllowances = totalAllowances.add(salaryStructure.getBonus());
            }

            BigDecimal basicSalary = salaryStructure.getBasicSalary();
            BigDecimal grossPay = basicSalary.add(totalAllowances);

            BigDecimal otherDeductions = deductions.stream()
                    .map(EmployeeDeduction::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal monthlyTax = BigDecimal.ZERO;

        TaxSlab taxSlab = taxSlabRepository
                .findFirstByLowerBoundLessThanEqualAndUpperBoundGreaterThanEqual(grossPay, grossPay)
                .orElse(null);

            if (taxSlab != null && taxSlab.getRate() != null) {
                BigDecimal fixedNonTaxable = taxSlab.getFixedAmount() != null ? taxSlab.getFixedAmount() : BigDecimal.ZERO;

                BigDecimal amountToBeTaxed = grossPay.subtract(fixedNonTaxable);
                if (amountToBeTaxed.compareTo(BigDecimal.ZERO) < 0) {
                    amountToBeTaxed = BigDecimal.ZERO;
                }

                BigDecimal taxRate = taxSlab.getRate().divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
                monthlyTax = amountToBeTaxed.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
            }

            BigDecimal totalDeductions = otherDeductions.add(monthlyTax);
            BigDecimal netPay = grossPay.subtract(totalDeductions);

            Payroll payroll = new Payroll();
            payroll.setEmployee(employee.get());
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
            payrollDto.setEmployeeId(employee.get().getId());
            payrollDto.setEmployeeName(employee.get().getFullName());
            payrollDto.setTaxAmount(monthlyTax);

            message = messageService.getMessage(Messages.PAYROLL_CREATED_SUCCESSFULLY.getCode(), new String[]{}, locale);

            return buildResponse(201, true, message, payrollDto, null, null);
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
}
