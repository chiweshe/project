package com.example.employeemanagement.service.processor.api;

import com.example.employeemanagement.utils.requests.CreateEmployeeAllowanceRequest;
import com.example.employeemanagement.utils.responses.EmployeeAllowanceResponse;
import java.util.Locale;

public interface EmployeeAllowanceProcessor {

    EmployeeAllowanceResponse create(CreateEmployeeAllowanceRequest createEmployeeAllowanceRequest, Locale locale,
                                     String username);

    EmployeeAllowanceResponse findByEmployeeId(Long employeeId, Locale locale);
}
