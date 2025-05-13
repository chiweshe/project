package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.AllowanceServiceValidator;
import com.example.employeemanagement.utils.requests.CreateAllowanceRequest;
import org.springframework.util.StringUtils;

public class AllowanceServiceValidatorImpl implements AllowanceServiceValidator {

    @Override
    public boolean isRequestValid(CreateAllowanceRequest request) {
        if (request == null) {
            return false;
        }
        String name = request.getName();
        if (!StringUtils.hasText(name)) {
            return false;
        }
        if (name.length() < 3 || name.length() > 100) {
            return false;
        }
        String description = request.getDescription();
        if (!StringUtils.hasText(description)) {
            return false;
        }
        if (description.length() > 255) {
            return false;
        }

        if (request.getTaxable() == null) {
            return false;
        }

        return true;
    }
}
