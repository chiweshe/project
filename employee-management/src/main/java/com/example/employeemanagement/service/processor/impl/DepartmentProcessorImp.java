package com.example.employeemanagement.service.processor.impl;

import com.example.employeemanagement.business.logic.api.DepartmentService;
import com.example.employeemanagement.service.processor.api.DepartmentProcessor;
import com.example.employeemanagement.utils.requests.CreateDepartmentRequest;
import com.example.employeemanagement.utils.responses.DepartmentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import java.util.Locale;

public class DepartmentProcessorImp implements DepartmentProcessor {

    private final DepartmentService departmentService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public DepartmentProcessorImp(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public DepartmentResponse saveDepartment(CreateDepartmentRequest createDepartmentRequest, Locale locale, String username) {
        logger.info("Incoming request for saving department......");
        DepartmentResponse departmentResponse = departmentService.saveDepartment(createDepartmentRequest, locale, username);
        return departmentResponse;
    }

    @Override
    public DepartmentResponse findDepartmentById(Long id, Locale locale) {
        logger.info("Incoming request for finding department by ID: {}", id);
        return departmentService.findDepartmentById(id, locale);
    }

    @Override
    public DepartmentResponse deleteDepartmentById(Long id, Locale locale, String username) {
        logger.info("Incoming request for deleting department by ID: {}", id);
        return departmentService.deleteDepartmentById(id, locale, username);
    }

    @Override
    public DepartmentResponse getAllDepartmentsAsList(Locale locale) {
        logger.info("Incoming request for retrieving all departments as list.");
        return departmentService.getAllDepartmentsAsList(locale);
    }

    @Override
    public DepartmentResponse getAllDepartmentsAsPage(Pageable pageable, Locale locale) {
        logger.info("Incoming request for retrieving all departments as page.");
        return departmentService.getAllDepartmentsAsPage(pageable, locale);
    }
}
