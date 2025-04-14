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
    MESSAGE_USER_ROLE_RETRIEVED_SUCCESSFULLY("user.role.retrieved.successfully"),
    MESSAGE_USER_EXISTS("user.exists"),
    MESSAGE_USER_ACCOUNT_CREATION_SUCCESS("user.account.creation.successful"),
    MESSAGE_PASSWORD_CHANGE("password.change"),
    MESSAGE_ACCOUNT_CREATION_SUCCESS("user.account.creation.successful"),
    MESSAGE_USERNAME_PASSWORD_REQUIRED("username.password.required"),
    MESSAGE_USERNAME_INVALID("username.invalid"),
    MESSAGE_ACCOUNT_LOCKED("account.locked"),
    MESSAGE_USERNAME_PASSWORD_WRONG("username.password.wrong"),
    MESSAGE_ACCOUNT_NOT_ACTIVE("account.not.active"),
    MESSAGE_USER_ACCOUNT_SUCCESSFULLY_AUTHENTICATED("user.account.successfully.authenticated"),
    MESSAGE_SESSION_INVALID_SEARCH_REQUEST("session.invalid.search.request"),
    MESSAGE_SESSIONS_NOT_FOUND("sessions.not.found"),
    MESSAGE_SESSIONS_RETRIEVED_SUCCESSFULLY("sessions.retrieved.successfully"),
    MESSAGE_SESSION_INVALID_REQUEST("session.invalid.request"),
    MESSAGE_SESSION_CLOSED_SUCCESSFULLY("session.closed.successfully"),
    MESSAGE_ERROR_OCCURED("error.occured")
    , MESSAGE_SESSION_EXISTS("session.exists"),
    MESSAGE_SESSION_CREATED_SUCCESSFULLY("session.created.successfully"),;

    private String code;

    Messages(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
