package com.example.usermanagement.business.validation.api;

import com.example.usermanagement.utils.requests.CreateUserGroupRequest;

public interface UserGroupValidator {

    Boolean isRequestValid(CreateUserGroupRequest createUserGroupRequest);
}
