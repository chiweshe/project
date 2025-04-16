package com.example.employeemanagement.business.validation.api;

import com.example.employeemanagement.utils.requests.CreatePayslipRequest;

public interface PayslipServiceValidator {

    boolean isRequestValid(CreatePayslipRequest request);
}
