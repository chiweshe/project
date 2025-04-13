package com.example.usermanagement.business.validation.impl;

import com.example.usermanagement.business.validation.api.UserGroupValidator;
import com.example.usermanagement.utils.requests.CreateUserGroupRequest;

public class UserGroupValidatorImpl implements UserGroupValidator {

    @Override
    public Boolean isRequestValid(CreateUserGroupRequest createUserGroupRequest) {
        return true;
    }
}
