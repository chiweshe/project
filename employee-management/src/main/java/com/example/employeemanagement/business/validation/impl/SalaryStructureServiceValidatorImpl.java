package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.SalaryStructureServiceValidator;
import com.example.employeemanagement.utils.requests.CreateSalaryStructureRequest;
import java.math.BigDecimal;
import java.time.LocalDate;

public class SalaryStructureServiceValidatorImpl implements SalaryStructureServiceValidator {

    @Override
    public boolean isRequestValid(CreateSalaryStructureRequest request) {

        if (request == null) {
            return false;
        }
        if (request.getEmployeeId() == null || request.getEmployeeId() <= 0) {
            return false;
        }
        if (request.getBasicSalary() == null || request.getBasicSalary().compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        if (request.getBonus() != null && request.getBonus().compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }
        if (request.getEffectiveFrom() == null || request.getEffectiveFrom().isBefore(LocalDate.now())) {
            return false;
        }

        if (request.getActive() == null) {
            return false;
        }

        return true;
    }
}
