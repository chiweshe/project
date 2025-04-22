package com.example.employeemanagement.service.processor.impl;

import com.example.employeemanagement.business.logic.api.EmployeeAllowanceService;
import com.example.employeemanagement.service.processor.api.EmployeeAllowanceProcessor;
import com.example.employeemanagement.utils.requests.CreateEmployeeAllowanceRequest;
import com.example.employeemanagement.utils.responses.AllowanceResponse;
import com.example.employeemanagement.utils.responses.EmployeeAllowanceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class EmployeeAllowanceProcessorImpl implements EmployeeAllowanceProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EmployeeAllowanceService employeeAllowanceService;

    public EmployeeAllowanceProcessorImpl(EmployeeAllowanceService employeeAllowanceService) {
        this.employeeAllowanceService = employeeAllowanceService;
    }

    @Override
    public EmployeeAllowanceResponse create(CreateEmployeeAllowanceRequest createEmployeeAllowanceRequest, Locale locale, String username) {
        logger.info("Incoming request for saving employee allowance......");

        EmployeeAllowanceResponse employeeAllowanceResponse = employeeAllowanceService.create(createEmployeeAllowanceRequest, locale, username);

        return employeeAllowanceResponse;
    }

    @Override
    public EmployeeAllowanceResponse findByEmployeeId(Long employeeId, Locale locale) {
        logger.info("Incoming request for finding allowances by employee id: {}", employeeId);
        return employeeAllowanceService.findByEmployeeId(employeeId, locale);
    }
}
