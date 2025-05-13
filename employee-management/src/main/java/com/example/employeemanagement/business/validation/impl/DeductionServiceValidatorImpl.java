package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.DeductionServiceValidator;
import com.example.employeemanagement.business.validation.api.DepartmentServiceValidator;
import com.example.employeemanagement.utils.requests.CreateDeductionRequest;

public class DeductionServiceValidatorImpl implements DeductionServiceValidator {

    @Override
    public boolean isRequestValid(CreateDeductionRequest request) {

        //to add validation
        return true;
    }
}
