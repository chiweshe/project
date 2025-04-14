package com.example.usermanagement.business.validation.api;

import com.example.usermanagement.utils.requests.CreateUserRequest;

public interface UserRegistrationValidator {

    Boolean isRequestValid(CreateUserRequest createUserRequest);
}
