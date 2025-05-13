package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.PayrollServiceValidator;
import com.example.employeemanagement.utils.requests.CreatePayrollRequest;

public class PayrollServiceValidatorImpl implements PayrollServiceValidator {

    @Override
    public boolean isRequestValid(CreatePayrollRequest request) {
        //to add validation

        return true;
    }
}
