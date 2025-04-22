package com.example.employeemanagement.service.processor.api;

import com.example.employeemanagement.utils.requests.CreateEmployeeAllowanceRequest;
import com.example.employeemanagement.utils.requests.CreateEmployeeDeductionRequest;
import com.example.employeemanagement.utils.responses.EmployeeAllowanceResponse;
import com.example.employeemanagement.utils.responses.EmployeeDeductionResponse;

import java.util.Locale;

public interface EmployeeDeductionProcessor {

    EmployeeDeductionResponse create(CreateEmployeeDeductionRequest createEmployeeDeductionRequest, Locale locale,
                                     String username);

    EmployeeDeductionResponse findByEmployeeId(Long employeeId, Locale locale);
}
