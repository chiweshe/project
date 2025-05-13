package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.EmployeeDeductionValidator;
import com.example.employeemanagement.utils.requests.CreateEmployeeDeductionRequest;

public class EmployeeDeductionValidatorImpl implements EmployeeDeductionValidator {

    @Override
    public boolean isRequestValid(CreateEmployeeDeductionRequest request) {

        //to add validation

        return true;
    }
}
