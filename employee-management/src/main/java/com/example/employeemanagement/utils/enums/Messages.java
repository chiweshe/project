package com.example.employeemanagement.utils.enums;

public enum Messages {

    TEST("messages.test"),
    INVALID_REQUEST_SUPPLIED("messages.invalid_request_supplied"),
    DEPARTMENT_ALREADY_EXISTS("messages.department_already_exists"),
    DEPARTMENT_CREATED_SUCCESSFULLY("messages.department_created_successfully"),
    EMPLOYEE_ALREADY_EXISTS("messages.employee_already_exists"),
    EMPLOYEE_CREATED_SUCCESSFULLY("messages.employee_created_successfully"),
    DEPARTMENT_NOT_FOUND("messages.department_not_found"),
    DEPARTMENT_DELETED_SUCCESSFULLY("messages.department_deleted_successfully"),
    DEPARTMENT_RETRIEVED_SUCCESSFUL("messages.department_retrieved_successfully"),
    DEPARTMENTS_RETRIEVED_SUCCESSFUL("messages.departments_retrieved_successfully"),
    EMPLOYEE_NOT_FOUND("messages.employee_not_found"),
    EMPLOYEE_DELETED_SUCCESSFULLY("messages.employee_deleted_successfully"),
    EMPLOYEES_RETRIEVED_SUCCESSFULLY("messages.employees_retrieved_successfully"),
    EMPLOYEE_RETRIEVED_SUCCESSFULLY("messages.employee_retrieved_successfully"),;

    private String code;

    Messages(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
