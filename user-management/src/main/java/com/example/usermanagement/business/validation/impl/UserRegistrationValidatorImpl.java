package com.example.usermanagement.business.validation.impl;

import com.example.usermanagement.business.validation.api.UserRegistrationValidator;
import com.example.usermanagement.utils.requests.CreateUserRequest;

public class UserRegistrationValidatorImpl implements UserRegistrationValidator {

    @Override
    public Boolean isRequestValid(CreateUserRequest createUserRequest) {
        return true;
    }
}
