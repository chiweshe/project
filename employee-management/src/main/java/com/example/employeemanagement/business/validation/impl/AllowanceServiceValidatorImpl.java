package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.AllowanceServiceValidator;
import com.example.employeemanagement.utils.requests.CreateAllowanceRequest;

public class AllowanceServiceValidatorImpl implements AllowanceServiceValidator {

    @Override
    public boolean isRequestValid(CreateAllowanceRequest request) {
        return true;
    }
}
