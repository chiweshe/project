package com.example.usermanagement.business.validation.impl;

import com.example.usermanagement.business.validation.api.UserSessionValidatorService;
import com.example.usermanagement.utils.requests.CreateSessionRequest;
import com.example.usermanagement.utils.requests.LogoutRequest;

public class UserSessionValidatorServiceImpl implements UserSessionValidatorService {
    @Override
    public Boolean isCreateSessionRequestValid(CreateSessionRequest createSessionRequest) {
        if(createSessionRequest == null){
            return false;
        }

        if(createSessionRequest.getUserName() == null ||
                createSessionRequest.getToken() == null){
            return false;
        }

        if(createSessionRequest.getUserName().trim().isEmpty() ||
                createSessionRequest.getToken().trim().isEmpty()){
            return false;
        }

        return true;
    }

    @Override
    public boolean isLogoutValid(LogoutRequest logoutRequest) {

        if(logoutRequest == null){
            return false;
        }

        if(logoutRequest.getUsername() == null || logoutRequest.getClassification() == null){
            return false;
        }

        if (logoutRequest.getUsername().trim().isEmpty() || logoutRequest.getClassification().trim().isEmpty()){
            return false;
        }

        return true;
    }
}
