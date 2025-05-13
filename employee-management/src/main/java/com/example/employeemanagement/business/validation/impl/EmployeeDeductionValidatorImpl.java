package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.EmployeeDeductionValidator;
import com.example.employeemanagement.utils.requests.CreateEmployeeDeductionRequest;
import java.math.BigDecimal;

public class EmployeeDeductionValidatorImpl implements EmployeeDeductionValidator {

    @Override
    public boolean isRequestValid(CreateEmployeeDeductionRequest request) {

        if (request == null) {
            return false;
        }

        if (request.getEmployeeId() == null || request.getEmployeeId() <= 0) {
            return false;
        }

        if (request.getDeductionId() == null || request.getDeductionId() <= 0) {
            return false;
        }

        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        return true;
    }
}
