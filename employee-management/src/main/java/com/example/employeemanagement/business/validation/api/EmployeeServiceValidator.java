package com.example.employeemanagement.business.validation.api;

import com.example.employeemanagement.utils.requests.CreateEmployeeRequest;


public interface EmployeeServiceValidator {

    boolean isRequestValid(CreateEmployeeRequest createEmployeeRequest);

    String getErrorMessage(CreateEmployeeRequest createEmployeeRequest);
}
