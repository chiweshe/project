package com.example.employeemanagement.business.validation.api;

import com.example.employeemanagement.utils.requests.CreateSalaryStructureRequest;

public interface SalaryStructureServiceValidator {

    boolean isRequestValid(CreateSalaryStructureRequest request);

}
