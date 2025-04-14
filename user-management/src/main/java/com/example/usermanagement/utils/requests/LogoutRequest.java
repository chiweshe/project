package com.example.usermanagement.utils.requests;

public class LogoutRequest {

    private String username;
    private String appCode;
    private String classification;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public String toString() {
        return "LogoutRequest{" +
                "username='" + username + '\'' +
                ", appCode='" + appCode + '\'' +
                ", classification='" + classification + '\'' +
                '}';
    }
}
