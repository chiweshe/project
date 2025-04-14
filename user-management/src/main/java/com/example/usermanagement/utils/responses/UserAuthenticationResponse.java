package com.example.usermanagement.utils.responses;

import com.example.usermanagement.utils.dto.UserRegistrationDto;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAuthenticationResponse {

    private String message;

    private int statusCode;

    private Boolean authenticated;

    private UserRegistrationDto userRegistrationDto;

    private String Token;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public UserRegistrationDto getUserRegistrationDto() {
        return userRegistrationDto;
    }

    public void setUserRegistrationDto(UserRegistrationDto userRegistrationDto) {
        this.userRegistrationDto = userRegistrationDto;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    @Override
    public String toString() {
        return "UserAuthenticationResponse{" +
                "message='" + message + '\'' +
                ", statusCode=" + statusCode +
                ", authenticated=" + authenticated +
                ", userRegistrationDto=" + userRegistrationDto +
                ", Token='" + Token + '\'' +
                '}';
    }
}
