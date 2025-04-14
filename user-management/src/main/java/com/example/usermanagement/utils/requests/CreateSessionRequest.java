package com.example.usermanagement.utils.requests;

import java.time.LocalDateTime;

public class CreateSessionRequest {
    private String userName;
    private String token;
    private LocalDateTime expiryTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    @Override
    public String toString() {
        return "CreateSessionRequest{" +
                "userName='" + userName + '\'' +
                ", token='" + token + '\'' +
                ", expiryTime=" + expiryTime +
                '}';
    }
}
