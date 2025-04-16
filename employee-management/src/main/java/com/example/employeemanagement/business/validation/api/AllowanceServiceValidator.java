package com.example.employeemanagement.business.validation.api;

import com.example.employeemanagement.utils.requests.CreateAllowanceRequest;

public interface AllowanceServiceValidator {

    boolean isRequestValid(CreateAllowanceRequest request);
}
