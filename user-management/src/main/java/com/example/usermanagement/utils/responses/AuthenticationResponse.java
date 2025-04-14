package com.example.usermanagement.utils.responses;

public class AuthenticationResponse {

    private boolean success;

    private String message;

    private int statusCode;

    private String token;

    private ResponseBody responseBody;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", statusCode=" + statusCode +
                ", token='" + token + '\'' +
                ", responseBody=" + responseBody +
                '}';
    }
}
