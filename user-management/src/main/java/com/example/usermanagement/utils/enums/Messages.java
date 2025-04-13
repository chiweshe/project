package com.example.usermanagement.utils.enums;

public enum Messages {
    INVALID_REQUEST("invalid.request"),
    ROLE_ALREADY_EXIST("role.already_exist"),
    USER_ROLE_CREATED_SUCCESSFULLY("userRole.created.successfully"),
    GROUP_ALREADY_EXIST("group.already_exist"),
    USER_GROUP_CREATED_SUCCESSFULLY("user_group.created.successfully"),
    MESSAGE_INVALID_REQUEST("invalid.request"),
    MESSAGE_INVALID_ASSIGN_ROLES_REQUEST("invalid.assign.roles.request"),
    MESSAGE_SOME_OR_ALL_USER_ROLES_INVALID("some.or.all.user.roles.invalid"),
    MESSAGE_ROLE_ASSIGNMENT_SUCCESS("role.assignment.success"),
    MESSAGE_USER_GROUP_NOT_FOUND("user.group.not.found"),
    MESSAGE_USER_ROLE_NOT_FOUND("User.role.not.found"),
    MESSAGE_USER_GROUP_RETRIEVED_SUCCESSFULLY("userGroup.retrieved.successfully"),
    MESSAGE_USER_ROLE_RETRIEVED_SUCCESSFULLY("user.role.retrieved.successfully"),;

    private String code;

    Messages(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
