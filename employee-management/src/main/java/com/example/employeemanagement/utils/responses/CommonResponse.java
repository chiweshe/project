package com.example.employeemanagement.utils.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse {

    private int statusCode;
    private boolean success;
    private String message;
    private List<String> errorMessages;

    public CommonResponse() {
    }

    public CommonResponse(int statusCode, boolean success, String message) {
        this.statusCode = statusCode;
        this.success = success;
        this.message = message;
    }

    public CommonResponse(int statusCode, boolean success, String message, List<String> errorMessages) {
        this.statusCode = statusCode;
        this.success = success;
        this.message = message;
        this.errorMessages = errorMessages;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

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

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    @Override
    public String toString() {
        return "CommonResponse{" + "statusCode=" + statusCode + ", success=" + success + ", message='" + message +
                '\'' + ", errorMessages=" + errorMessages + '}';
    }
}
