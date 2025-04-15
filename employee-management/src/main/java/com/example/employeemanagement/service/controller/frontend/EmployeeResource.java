package com.example.employeemanagement.service.controller.frontend;


import com.example.employeemanagement.business.logic.api.EmployeeService;
import com.example.employeemanagement.service.processor.api.DepartmentProcessor;
import com.example.employeemanagement.service.processor.api.EmployeeProcessor;
import com.example.employeemanagement.utils.constants.Constants;
import com.example.employeemanagement.utils.requests.CreateDepartmentRequest;
import com.example.employeemanagement.utils.requests.CreateEmployeeRequest;
import com.example.employeemanagement.utils.responses.DepartmentResponse;
import com.example.employeemanagement.utils.responses.EmployeeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@CrossOrigin
@RequestMapping("/payroll-management/v1/employee")
public class EmployeeResource {

    private final EmployeeProcessor employeeProcessor;

    public EmployeeResource(EmployeeProcessor employeeProcessor) {
        this.employeeProcessor = employeeProcessor;
    }

    @Operation(summary = "Create a department")
    @PostMapping(value = "")
    public EmployeeResponse createEmployee(@Valid @RequestBody final CreateEmployeeRequest createEmployeeRequest,
                                             @Parameter(name = "Authorization", in = ParameterIn.HEADER,
                                                       description = "Bearer token", required = true)
                                               String authenticationToken,
                                             @Parameter(description = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                               @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                       defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return employeeProcessor.save(createEmployeeRequest,  locale, authenticationToken);
    }
}


