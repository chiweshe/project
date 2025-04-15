package com.example.employeemanagement.business.logic.imp;

import com.example.employeemanagement.business.logic.api.EmployeeService;
import com.example.employeemanagement.business.validation.api.EmployeeServiceValidator;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.utils.dto.EmployeeDto;
import com.example.employeemanagement.utils.enums.Messages;
import com.example.employeemanagement.utils.messages.api.MessageService;
import com.example.employeemanagement.utils.requests.CreateEmployeeRequest;
import com.example.employeemanagement.utils.responses.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Locale;

public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;
    private final EmployeeServiceValidator employeeServiceValidator;
    private final MessageService messageService;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeServiceValidator employeeServiceValidator,
                               MessageService messageService, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeServiceValidator = employeeServiceValidator;
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeResponse create(CreateEmployeeRequest createEmployeeRequest, Locale locale, String username) {

        String message = "";

        boolean isRequestValid = employeeServiceValidator.isRequestValid(createEmployeeRequest);
        if (!isRequestValid) {

            message = messageService.getMessage(Messages.INVALID_REQUEST_SUPPLIED.getCode(), new String[]{},
                    locale);
            return buildResponse(400, false, message, null, null,
                    null);

        }
        return null;
    }

        EmployeeResponse buildResponse(int statusCode, Boolean success, String message, EmployeeDto employeeDto,
                                       List<EmployeeDto> employeeDtoList, Page<EmployeeDto> employeeDtoPage){
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setStatusCode(statusCode);
        employeeResponse.setSuccess(success);
        employeeResponse.setMessage(message);
        employeeResponse.setEmployeeDtoList(employeeDtoList);
        employeeResponse.setEmployeeDto(employeeDto);
        employeeResponse.setEmployeeDtoPage(employeeDtoPage);

        return employeeResponse;

        }

}
