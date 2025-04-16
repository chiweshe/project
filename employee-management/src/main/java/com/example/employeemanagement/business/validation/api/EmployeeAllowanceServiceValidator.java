package com.example.employeemanagement.business.validation.api;

import com.example.employeemanagement.utils.requests.CreateEmployeeAllowanceRequest;

public interface EmployeeAllowanceServiceValidator {

    boolean isRequestValid(CreateEmployeeAllowanceRequest request);
}
