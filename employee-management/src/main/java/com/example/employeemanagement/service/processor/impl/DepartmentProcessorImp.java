package com.example.employeemanagement.service.processor.impl;

import com.example.employeemanagement.business.logic.api.DepartmentService;
import com.example.employeemanagement.service.processor.api.DepartmentProcessor;
import com.example.employeemanagement.utils.requests.CreateDepartmentRequest;
import com.example.employeemanagement.utils.responses.DepartmentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
}
