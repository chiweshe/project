package com.example.usermanagement.business.validation.api;

import com.example.usermanagement.utils.requests.CreateUserRoleRequest;

public interface UserRoleValidator {

    boolean isRequestValid(CreateUserRoleRequest createUserRoleRequest);

}
