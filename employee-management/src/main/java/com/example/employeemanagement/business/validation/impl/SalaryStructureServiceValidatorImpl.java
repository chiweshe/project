package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.SalaryStructureServiceValidator;
import com.example.employeemanagement.utils.requests.CreateSalaryStructureRequest;

public class SalaryStructureServiceValidatorImpl implements SalaryStructureServiceValidator {

    @Override
    public boolean isRequestValid(CreateSalaryStructureRequest request) {
        return true;
    }
}
