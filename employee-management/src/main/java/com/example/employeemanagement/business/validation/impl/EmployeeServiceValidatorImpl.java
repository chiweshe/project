package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.EmployeeServiceValidator;
import com.example.employeemanagement.utils.requests.CreateEmployeeRequest;

public class EmployeeServiceValidatorImpl implements EmployeeServiceValidator {

    @Override
    public boolean isRequestValid(CreateEmployeeRequest createEmployeeRequest) {

        return true;
    }

}
