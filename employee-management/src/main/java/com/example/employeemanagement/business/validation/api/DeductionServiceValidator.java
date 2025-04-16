package com.example.employeemanagement.business.validation.api;

import com.example.employeemanagement.utils.requests.CreateDeductionRequest;

public interface DeductionServiceValidator {

    boolean isRequestValid(CreateDeductionRequest request);
}
