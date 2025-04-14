package com.example.usermanagement.service.processor.impl;

import com.example.usermanagement.business.logic.api.UserSessionService;
import com.example.usermanagement.service.processor.api.UserSessionProcessor;
import com.example.usermanagement.utils.requests.CreateSessionRequest;
import com.example.usermanagement.utils.requests.LogoutRequest;
import com.example.usermanagement.utils.responses.UserSessionsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class UserSessionProcessorImpl implements UserSessionProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserSessionService userSessionService;
    public UserSessionProcessorImpl(UserSessionService userSessionService) {
        this.userSessionService = userSessionService;
    }

    @Override
    public UserSessionsResponse save(CreateSessionRequest createSessionRequest, Locale locale, String username) {
        logger.info("Incoming request to create a session : {}", createSessionRequest);

        UserSessionsResponse userSessionsResponse = userSessionService.createSession(createSessionRequest, locale, username);

        logger.info("Outgoing response for creating a session : {}", userSessionsResponse);

        return userSessionsResponse;
    }

    @Override
    public UserSessionsResponse findById(Long id, Locale locale) {
        logger.info("Incoming request to find session by id {}", id);

        UserSessionsResponse userSessionsResponse = userSessionService.checkSession(id, locale);

        logger.info("outgoing response for find session by id {}", userSessionsResponse);

        return userSessionsResponse;
    }

    @Override
    public UserSessionsResponse logout(LogoutRequest logoutRequest, Locale locale, String username) {
        logger.info("Incoming request to delete a session : {}", logoutRequest);

        UserSessionsResponse userSessionsResponse = userSessionService.logout(logoutRequest, locale);

        logger.info("Outgoing response for deleting a session : {}", userSessionsResponse);

        return userSessionsResponse;
    }
}
