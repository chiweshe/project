package com.example.usermanagement.business.validation.api;

import com.example.usermanagement.utils.requests.CreateSessionRequest;
import com.example.usermanagement.utils.requests.LogoutRequest;

public interface UserSessionValidatorService {
    public Boolean isCreateSessionRequestValid(CreateSessionRequest createSessionRequest);
    public boolean isLogoutValid(LogoutRequest logoutRequest);
}
