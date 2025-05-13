package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.DepartmentServiceValidator;
import com.example.employeemanagement.utils.requests.CreateDepartmentRequest;
import org.springframework.util.StringUtils;

public class DepartmentServiceValidatorImpl implements DepartmentServiceValidator {

    @Override
    public boolean isRequestValid(CreateDepartmentRequest createDepartmentRequest) {

        if (createDepartmentRequest == null) {
            return false;
        }

        if (!StringUtils.hasText(createDepartmentRequest.getName())) {
            return false;
        }

        return true;
    }

}
