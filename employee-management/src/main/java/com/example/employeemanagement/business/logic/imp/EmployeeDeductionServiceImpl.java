package com.example.employeemanagement.business.logic.imp;

import com.example.employeemanagement.business.logic.api.EmployeeDeductionService;
import com.example.employeemanagement.business.validation.api.EmployeeDeductionValidator;
import com.example.employeemanagement.domain.*;
import com.example.employeemanagement.repository.DeductionsRepository;
import com.example.employeemanagement.repository.EmployeeDeductionRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.utils.dto.EmployeeAllowanceDto;
import com.example.employeemanagement.utils.dto.EmployeeDeductionDto;
import com.example.employeemanagement.utils.dto.PayrollDto;
import com.example.employeemanagement.utils.enums.Messages;
import com.example.employeemanagement.utils.messages.api.MessageService;
import com.example.employeemanagement.utils.requests.CreateEmployeeDeductionRequest;
import com.example.employeemanagement.utils.responses.EmployeeDeductionResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class EmployeeDeductionServiceImpl implements EmployeeDeductionService {

    private final EmployeeDeductionRepository employeeDeductionRepository;
    private final EmployeeDeductionValidator employeeDeductionValidator;
    private final EmployeeRepository employeeRepository;
    private final DeductionsRepository deductionsRepository;
    private final MessageService messageService;
    private final ModelMapper modelMapper;

    public EmployeeDeductionServiceImpl(EmployeeDeductionRepository employeeDeductionRepository, EmployeeDeductionValidator employeeDeductionValidator,
                                        EmployeeRepository employeeRepository, DeductionsRepository deductionsRepository,
                                        MessageService messageService, ModelMapper modelMapper) {
        this.employeeDeductionRepository = employeeDeductionRepository;
        this.employeeDeductionValidator = employeeDeductionValidator;
        this.employeeRepository = employeeRepository;
        this.deductionsRepository = deductionsRepository;
        this.messageService = messageService;
        this.modelMapper = modelMapper;

}

    @Override
    public EmployeeDeductionResponse create(CreateEmployeeDeductionRequest createEmployeeDeductionRequest, Locale locale) {

    String message ="";

    boolean isRequestValid = employeeDeductionValidator.isRequestValid(createEmployeeDeductionRequest);

        if (!isRequestValid){

        message = messageService.getMessage(Messages.INVALID_REQUEST_SUPPLIED.getCode(), new String[]{},
                locale);
        return buildResponse(400, false, message, null, null,
                null);

    }

    Optional<Employee> employeeRetrieved = employeeRepository.findByIdAndStatusNot(createEmployeeDeductionRequest.getEmployeeId(),
            Status.DELETED);
        if (employeeRetrieved.isEmpty()) {
        message = messageService.getMessage(Messages.EMPLOYEE_NOT_FOUND.getCode(), new String[]{},
                locale);
        return buildResponse(400, false, message, null, null,
                null);
    }

    Optional<Deduction> deductionRetrieved = deductionsRepository.findByIdAndStatusNot(createEmployeeDeductionRequest.
                    getDeductionId(), Status.DELETED);
        if (deductionRetrieved.isEmpty()) {
        message = messageService.getMessage(Messages.DEDUCTION_NOT_FOUND.getCode(), new String[]{}, locale);
        return buildResponse(404, false, message, null,
                null, null);
    }

    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    EmployeeDeduction employeeDeductionToBeSaved = modelMapper.map(createEmployeeDeductionRequest, EmployeeDeduction.class);
    employeeDeductionToBeSaved.setDeduction(deductionRetrieved.get());
    employeeDeductionToBeSaved.setDeductionName(deductionRetrieved.get().getName());
    employeeDeductionToBeSaved.setEmployee(employeeRetrieved.get());
    employeeDeductionToBeSaved.setEmployeeName(employeeRetrieved.get().getFullName());


    EmployeeDeduction employeeDeductionSaved = employeeDeductionRepository.save(employeeDeductionToBeSaved);

    EmployeeDeductionDto employeeDeductionDtoReturned = modelMapper.map(employeeDeductionSaved, EmployeeDeductionDto.class);
    employeeDeductionDtoReturned.setDeductionName(deductionRetrieved.get().getName());
//        PayrollDto payrollDto = modelMapper.map(savedPayroll, PayrollDto.class);
//        payrollDto.setEmployeeId(employee.get().getId());
//        payrollDto.setEmployeeName(employee.get().getFullName());
//        payrollDto.setTaxAmount(monthlyTax);
    message = messageService.getMessage(Messages.EMPLOYEE_DEDUCTION_CREATED_SUCCESSFULLY.getCode(), new String[]{}, locale);
        return buildResponse(201, true, message, employeeDeductionDtoReturned, null,
            null);

}

    @Override
    public EmployeeDeductionResponse findByEmployeeId(Long employeeId, Locale locale) {

        String message = "";

        List<EmployeeDeduction> employeeDeductions =
                employeeDeductionRepository.findByEmployeeIdAndStatusNot(employeeId, Status.DELETED);

        if (employeeDeductions.isEmpty()) {
            message = messageService.getMessage(Messages.MESSAGE_DEDUCTIONS_NOT_FOUND.getCode(), new String[]{},
                    locale);
            return buildResponse(404, false, message, null,
                    null, null);
        }

        List<EmployeeDeductionDto> employeeDeductionDtoListReturned = modelMapper.map(employeeDeductions,
                new TypeToken<List<EmployeeDeductionDto>>() {}.getType());

        message = messageService.getMessage(Messages.MESSAGE_EMPLOYEE_DEDUCTION_RETRIEVED_SUCCESSFULLY.getCode(),
                new String[]{},
                locale);
        return buildResponse(200, true, message, null,
                employeeDeductionDtoListReturned, null);

    }


    private EmployeeDeductionResponse buildResponse(int statusCode, Boolean success , String message,
                                                    EmployeeDeductionDto employeeDeductionDto, List<EmployeeDeductionDto> employeeDeductionDtoList,
                                                    Page<EmployeeDeductionDto> employeeDeductionDtoPage) {

        EmployeeDeductionResponse employeeDeductionResponse = new EmployeeDeductionResponse();
        employeeDeductionResponse.setStatusCode(statusCode);
        employeeDeductionResponse.setSuccess(success);
        employeeDeductionResponse.setMessage(message);
        employeeDeductionResponse.setEmployeeDeductionDto(employeeDeductionDto);
        employeeDeductionResponse.setEmployeeDeductionDtoList(employeeDeductionDtoList);
        employeeDeductionResponse.setEmployeeDeductionDtoPage(employeeDeductionDtoPage);

        return employeeDeductionResponse;
    }
}
