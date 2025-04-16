package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.PayslipServiceValidator;
import com.example.employeemanagement.utils.requests.CreatePayslipRequest;

public class PayslipServiceValidatorImpl implements PayslipServiceValidator {

    @Override
    public boolean isRequestValid(CreatePayslipRequest request) {
        return true;
    }
}
