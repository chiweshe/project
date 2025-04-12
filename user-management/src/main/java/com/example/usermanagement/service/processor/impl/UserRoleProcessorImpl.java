package com.example.usermanagement.service.processor.impl;

import com.example.usermanagement.business.logic.api.UserRoleService;
import com.example.usermanagement.service.processor.api.UserRoleProcessor;
import com.example.usermanagement.utils.requests.CreateUserRoleRequest;
import com.example.usermanagement.utils.responses.UserRoleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Locale;

public class UserRoleProcessorImpl implements UserRoleProcessor {

    private final UserRoleService userRoleService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserRoleProcessorImpl(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Override
    public UserRoleResponse create(CreateUserRoleRequest createUserRoleRequest, Locale locale, String username) {
        logger.info("Incoming request to create a user role : {}", createUserRoleRequest);
        UserRoleResponse userRoleResponse = userRoleService.create(createUserRoleRequest,locale, username);
        logger.info("Outgoing response for create a role : {}", userRoleResponse);
        return userRoleResponse;
    }
}
