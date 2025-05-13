package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.PayrollServiceValidator;
import com.example.employeemanagement.utils.requests.CreatePayrollRequest;
import java.time.YearMonth;


public class PayrollServiceValidatorImpl implements PayrollServiceValidator {

    @Override
    public boolean isRequestValid(CreatePayrollRequest request) {

        if (request == null) {
            return false;
        }
        if (request.getEmployeeId() == null || request.getEmployeeId() <= 0) {
            return false;
        }

        YearMonth payrollMonth = request.getPayrollMonth();

        if (payrollMonth == null || payrollMonth.isAfter(YearMonth.now())) {
            return false;
        }

        return true;
    }
}
