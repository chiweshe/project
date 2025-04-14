package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.DepartmentServiceValidator;
import com.example.employeemanagement.utils.requests.CreateDepartmentRequest;

public class DepartmentServiceValidatorImpl implements DepartmentServiceValidator {

    @Override
    public boolean isRequestValid(CreateDepartmentRequest createDepartmentRequest) {
        return true;
    }
}
