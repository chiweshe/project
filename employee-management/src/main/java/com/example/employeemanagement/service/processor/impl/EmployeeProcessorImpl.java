package com.example.employeemanagement.service.processor.impl;

import com.example.employeemanagement.business.logic.api.EmployeeService;
import com.example.employeemanagement.service.processor.api.EmployeeProcessor;
import com.example.employeemanagement.utils.requests.CreateEmployeeRequest;
import com.example.employeemanagement.utils.responses.EmployeeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Locale;

public class EmployeeProcessorImpl implements EmployeeProcessor {

    private final EmployeeService employeeService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public EmployeeProcessorImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public EmployeeResponse save(CreateEmployeeRequest createEmployeeRequest, Locale locale, String username) {
        logger.info("Incoming request to save employee....");
        EmployeeResponse employeeResponse = employeeService.create(createEmployeeRequest, locale, username);
        return employeeResponse;
    }
}
