package com.example.employeemanagement.business.logic.api;

import com.example.employeemanagement.utils.requests.CreateEmployeeDeductionRequest;
import com.example.employeemanagement.utils.responses.EmployeeDeductionResponse;
import java.util.Locale;

public interface EmployeeDeductionService {

    EmployeeDeductionResponse create(CreateEmployeeDeductionRequest createEmployeeDeductionRequest, Locale locale);

    EmployeeDeductionResponse findByEmployeeId(Long employeeId, Locale locale);
}
