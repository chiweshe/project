package com.example.usermanagement.service.processor.api;

import com.example.usermanagement.utils.requests.CreateUserRequest;
import com.example.usermanagement.utils.responses.UserRegistrationResponse;
import java.util.Locale;

public interface UserRegistrationProcessor {

    public UserRegistrationResponse registerUser(CreateUserRequest createUserRequest, Locale locale, String username);

}
