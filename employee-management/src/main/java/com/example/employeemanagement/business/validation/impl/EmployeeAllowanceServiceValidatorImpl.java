package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.EmployeeAllowanceServiceValidator;
import com.example.employeemanagement.utils.requests.CreateEmployeeAllowanceRequest;

public class EmployeeAllowanceServiceValidatorImpl implements EmployeeAllowanceServiceValidator {

    @Override
    public boolean isRequestValid(CreateEmployeeAllowanceRequest request) {

        //to add validation
        return true;
    }
}
