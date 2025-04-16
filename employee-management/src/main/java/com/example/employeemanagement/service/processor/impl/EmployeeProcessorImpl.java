package com.example.employeemanagement.service.processor.impl;

import com.example.employeemanagement.business.logic.api.EmployeeService;
import com.example.employeemanagement.service.processor.api.EmployeeProcessor;
import com.example.employeemanagement.utils.requests.CreateEmployeeRequest;
import com.example.employeemanagement.utils.responses.EmployeeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
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

    @Override
    public EmployeeResponse findById(Long id, Locale locale) {
        logger.info("Incoming request to find employee by ID: {}", id);
        return employeeService.findById(id, locale);
    }

    @Override
    public EmployeeResponse delete(Long id, Locale locale, String username) {
        logger.info("Incoming request to delete employee by ID: {}", id);
        return employeeService.delete(id, locale, username);
    }

    @Override
    public EmployeeResponse findAll(Locale locale) {
        logger.info("Incoming request to list all employees");
        return employeeService.findAllAsList(locale);
    }

    @Override
    public EmployeeResponse findAllAsPages(Pageable pageable, Locale locale) {
        logger.info("Incoming request to fetch paginated employee list - Page: {}, Size: {}",pageable, locale);
        return employeeService.findAllAsPage(pageable, locale);
    }

}
