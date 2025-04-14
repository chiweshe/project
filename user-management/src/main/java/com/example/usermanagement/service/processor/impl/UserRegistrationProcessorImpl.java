package com.example.usermanagement.service.processor.impl;

import com.example.usermanagement.business.logic.api.UserRegistrationService;
import com.example.usermanagement.service.processor.api.UserRegistrationProcessor;
import com.example.usermanagement.utils.requests.CreateUserRequest;
import com.example.usermanagement.utils.responses.UserRegistrationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Locale;

public class UserRegistrationProcessorImpl implements UserRegistrationProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRegistrationService userRegistrationService;

    public UserRegistrationProcessorImpl(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @Override
    public UserRegistrationResponse registerUser(CreateUserRequest createUserRequest, Locale locale, String username) {
        logger.info("Incoming request to create a user login account : {}", createUserRequest.toString());
        UserRegistrationResponse userRegistrationResponse = userRegistrationService.registerUser(createUserRequest,locale, username);
        logger.info("Outgoing response for a user login account  : {}", userRegistrationResponse);
        return userRegistrationResponse;
    }
}
