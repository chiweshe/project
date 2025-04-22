package com.example.employeemanagement.service.processor.impl;

import com.example.employeemanagement.business.logic.api.EmployeeDeductionService;
import com.example.employeemanagement.service.processor.api.EmployeeDeductionProcessor;
import com.example.employeemanagement.utils.requests.CreateEmployeeDeductionRequest;
import com.example.employeemanagement.utils.responses.EmployeeDeductionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Locale;

public class EmployeeDeductionProcessorImpl  implements EmployeeDeductionProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final EmployeeDeductionService employeeDeductionService;

    public EmployeeDeductionProcessorImpl(EmployeeDeductionService employeeDeductionService) {
        this.employeeDeductionService = employeeDeductionService;
    }

    @Override
    public EmployeeDeductionResponse create(CreateEmployeeDeductionRequest createEmployeeDeductionRequest, Locale locale, String username) {
        logger.info("Incoming request for saving employee deduction......");

        EmployeeDeductionResponse employeeDeductionResponse = employeeDeductionService.create(createEmployeeDeductionRequest, locale);

        return employeeDeductionResponse;
    }

    @Override
    public EmployeeDeductionResponse findByEmployeeId(Long employeeId, Locale locale) {
        logger.info("Incoming request for finding deductions by employee id: {}", employeeId);
        return employeeDeductionService.findByEmployeeId(employeeId, locale);
    }
}
