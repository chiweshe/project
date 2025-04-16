package com.example.employeemanagement.business.validation.api;

import com.example.employeemanagement.utils.requests.CreateEmployeeDeductionRequest;

public interface EmployeeDeductionValidator {

    boolean isRequestValid(CreateEmployeeDeductionRequest request);
}
