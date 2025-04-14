package com.example.employeemanagement.business.validation.api;

import com.example.employeemanagement.utils.requests.CreateDepartmentRequest;

public interface DepartmentServiceValidator {

    boolean isRequestValid(CreateDepartmentRequest createDepartmentRequest);
}
