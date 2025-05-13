package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.EmployeeAllowanceServiceValidator;
import com.example.employeemanagement.utils.requests.CreateEmployeeAllowanceRequest;

import java.math.BigDecimal;

public class EmployeeAllowanceServiceValidatorImpl implements EmployeeAllowanceServiceValidator {

    @Override
    public boolean isRequestValid(CreateEmployeeAllowanceRequest request) {

        if (request == null) {
            return false;
        }

        if (request.getEmployeeId() == null || request.getEmployeeId() <= 0) {
            return false;
        }

        if (request.getAllowanceId() == null || request.getAllowanceId() <= 0) {
            return false;
        }

        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        return true;
    }
}
