package com.example.usermanagement.business.logic.api;

import com.example.usermanagement.domain.Status;
import com.example.usermanagement.utils.requests.CreateSessionRequest;
import com.example.usermanagement.utils.requests.LogoutRequest;
import com.example.usermanagement.utils.responses.UserSessionsResponse;
import java.util.Locale;

public interface UserSessionService {

    public UserSessionsResponse checkSession(Long id, Locale locale);
    public UserSessionsResponse checkSessionByUsernameAndRecordStatus(String username, Status status);
    public UserSessionsResponse logout(LogoutRequest logoutRequest, Locale locale);
    public  UserSessionsResponse createSession(CreateSessionRequest createSessionRequest, Locale locale, String username);
}
