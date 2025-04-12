package com.example.usermanagement.business.validation.impl;

import com.example.usermanagement.business.validation.api.UserRoleValidator;
import com.example.usermanagement.utils.requests.CreateUserRoleRequest;

public class UserRoleValidatorImpl implements UserRoleValidator {
    @Override
    public boolean isRequestValid(CreateUserRoleRequest createUserRoleRequest) {
        return true;
    }
}
