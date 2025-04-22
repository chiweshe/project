package com.example.employeemanagement.service.controller.frontend;

import com.example.employeemanagement.service.processor.api.EmployeeDeductionProcessor;
import com.example.employeemanagement.utils.constants.Constants;
import com.example.employeemanagement.utils.requests.CreateEmployeeAllowanceRequest;
import com.example.employeemanagement.utils.requests.CreateEmployeeDeductionRequest;
import com.example.employeemanagement.utils.responses.EmployeeAllowanceResponse;
import com.example.employeemanagement.utils.responses.EmployeeDeductionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;

@RestController
@CrossOrigin
@RequestMapping("/payroll-management/v1/employee-deductions")
public class EmployeeDeductionResource {

    private final EmployeeDeductionProcessor employeeDeductionProcessor;

    public EmployeeDeductionResource(EmployeeDeductionProcessor employeeDeductionProcessor) {
        this.employeeDeductionProcessor = employeeDeductionProcessor;
    }


    @Operation(summary = "Create a employee deductions")
    @PostMapping(value = "")
    public EmployeeDeductionResponse create(@Valid @RequestBody final CreateEmployeeDeductionRequest createEmployeeDeductionRequest,
                                            @Parameter(name = "Authorization", in = ParameterIn.HEADER,
                                                    description = "Bearer token", required = true)
                                            String authenticationToken,
                                            @Parameter(description = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                            @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                    defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return employeeDeductionProcessor.create(createEmployeeDeductionRequest, locale, authenticationToken);
    }

    @Operation(summary = "Retrieve deductions by employee id")
    @GetMapping(value = "/employee-id/{employeeId}")
    public EmployeeDeductionResponse retrieve(@PathVariable(value ="employeeId") Long employeeId,
                                              @Parameter(name = "Authorization",
                                                      in = ParameterIn.HEADER,
                                                      description = "Bearer token", required = true)
                                              String authenticationToken,
                                              @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                      defaultValue = Constants.DEFAULT_LOCALE) Locale locale)
    {
        return employeeDeductionProcessor.findByEmployeeId(employeeId,locale);
    }

}
