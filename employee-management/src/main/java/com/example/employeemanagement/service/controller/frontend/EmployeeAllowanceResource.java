package com.example.employeemanagement.service.controller.frontend;

import com.example.employeemanagement.service.processor.api.EmployeeAllowanceProcessor;
import com.example.employeemanagement.utils.constants.Constants;
import com.example.employeemanagement.utils.requests.CreateDepartmentRequest;
import com.example.employeemanagement.utils.requests.CreateEmployeeAllowanceRequest;
import com.example.employeemanagement.utils.responses.DepartmentResponse;
import com.example.employeemanagement.utils.responses.EmployeeAllowanceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;

@RestController
@CrossOrigin
@RequestMapping("/payroll-management/v1/employee-allowances")
public class EmployeeAllowanceResource {

    private final EmployeeAllowanceProcessor employeeAllowanceProcessor;

    public EmployeeAllowanceResource(EmployeeAllowanceProcessor employeeAllowanceProcessor) {
        this.employeeAllowanceProcessor = employeeAllowanceProcessor;
    }


    @Operation(summary = "Create a employee allowance")
    @PostMapping(value = "")
    public EmployeeAllowanceResponse create(@Valid @RequestBody final CreateEmployeeAllowanceRequest createEmployeeAllowanceRequest,
                                               @Parameter(name = "Authorization", in = ParameterIn.HEADER,
                                                       description = "Bearer token", required = true)
                                               String authenticationToken,
                                               @Parameter(description = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                               @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                       defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return employeeAllowanceProcessor.create(createEmployeeAllowanceRequest,  locale, authenticationToken);
    }

    @Operation(summary = "Retrieve allowances by employee id")
    @GetMapping(value = "/employee-id/{employeeId}")
    public EmployeeAllowanceResponse retrieve(@PathVariable(value ="employeeId") Long employeeId,
                                              @Parameter(name = "Authorization",
                                                      in = ParameterIn.HEADER,
                                                      description = "Bearer token", required = true)
                                              String authenticationToken,
                                              @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                      defaultValue = Constants.DEFAULT_LOCALE) Locale locale)
    {
        return employeeAllowanceProcessor.findByEmployeeId(employeeId,locale);
    }

}
