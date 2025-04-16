package com.example.employeemanagement.business.validation.api;

import com.example.employeemanagement.utils.requests.CreatePayrollRequest;

public interface PayrollServiceValidator {

    boolean isRequestValid(CreatePayrollRequest request);
}
