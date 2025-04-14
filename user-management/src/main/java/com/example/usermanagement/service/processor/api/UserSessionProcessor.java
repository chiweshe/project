package com.example.usermanagement.service.processor.api;

import com.example.usermanagement.utils.requests.CreateSessionRequest;
import com.example.usermanagement.utils.requests.LogoutRequest;
import com.example.usermanagement.utils.responses.UserSessionsResponse;
import java.util.Locale;

public interface UserSessionProcessor {

    public UserSessionsResponse save(CreateSessionRequest createSessionRequest, Locale locale, String username);
    public UserSessionsResponse findById(Long id, Locale locale);
    public UserSessionsResponse logout(LogoutRequest logoutRequest, Locale locale, String username);
}
