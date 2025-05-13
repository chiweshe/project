package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.TaxSlabServiceValidator;
import com.example.employeemanagement.utils.requests.CreateTaxSlabRequest;
import java.math.BigDecimal;

public class TaxSlabServiceValidatorImpl implements TaxSlabServiceValidator {

    @Override
    public boolean validate(CreateTaxSlabRequest request) {

        if (request == null) {
            return false;
        }

        BigDecimal lowerBound = request.getLowerBound();
        BigDecimal upperBound = request.getUpperBound();
        BigDecimal rate = request.getRate();
        BigDecimal fixedAmount = request.getFixedAmount();

        if (lowerBound == null || upperBound == null || rate == null || fixedAmount == null) {
            return false;
        }

        if (lowerBound.compareTo(upperBound) >= 0) {
            return false;
        }

        if (rate.compareTo(BigDecimal.ZERO) < 0 || fixedAmount.compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }

        return true;
    }
}
