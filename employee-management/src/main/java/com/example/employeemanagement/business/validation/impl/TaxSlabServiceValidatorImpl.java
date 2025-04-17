package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.TaxSlabServiceValidator;
import com.example.employeemanagement.utils.requests.CreateTaxSlabRequest;

public class TaxSlabServiceValidatorImpl implements TaxSlabServiceValidator {

    @Override
    public boolean validate(CreateTaxSlabRequest createTaxSlabRequest) {
        return true;
    }
}
