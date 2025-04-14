package com.example.employeemanagement.utils.enums;

public enum Messages {

    TEST("messages.test"),
    INVALID_REQUEST_SUPPLIED("messages.invalid_request_supplied"),
    DEPARTMENT_ALREADY_EXISTS("messages.department_already_exists"),;

    private String code;

    Messages(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
