package com.example.usermanagement.utils.enums;

public enum Messages {
    INVALID_REQUEST("invalid.request"),
    ROLE_ALREADY_EXIST("role.already_exist"),
    USER_ROLE_CREATED_SUCCESSFULLY("userRole.created.successfully"),;

    private String code;

    Messages(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
