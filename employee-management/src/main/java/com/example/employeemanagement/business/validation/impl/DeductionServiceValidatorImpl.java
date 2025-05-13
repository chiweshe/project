package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.DeductionServiceValidator;
import com.example.employeemanagement.utils.requests.CreateDeductionRequest;
import org.springframework.util.StringUtils;

public class DeductionServiceValidatorImpl implements DeductionServiceValidator {

    @Override
    public boolean isRequestValid(CreateDeductionRequest request) {

        if (request == null) {
            return false;
        }

        if (!StringUtils.hasText(request.getName())) {
            return false;
        }

        if (request.getDescription() != null && !StringUtils.hasText(request.getDescription())) {
            return false;
        }

        return true;
    }

}
