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
    EMPLOYEE_RETRIEVED_SUCCESSFULLY("messages.employee_retrieved_successfully"),
    ALLOWANCE_ALREADY_EXISTS("messages.allowance_already_exists"),
    ALLOWANCE_CREATED_SUCCESSFULLY("messages.allowance_created_successfully"),
    ALLOWANCE_NOT_FOUND("messages.allowance_not_found"),
    ALLOWANCE_FOUND("messages.allowance_found"),
    ALLOWANCE_LIST_FETCHED("messages.allowance_list_fetched"),
    ALLOWANCE_DELETED_SUCCESSFULLY("messages.allowance_deleted_successfully"),
    DEDUCTION_CREATED_SUCCESSFULLY("messages.deduction_created_successfully"),
    DEDUCTION_ALREADY_EXISTS("messages.deduction_already_exists"),
    DEDUCTION_NOT_FOUND("messages.deduction_not_found"),
    DEDUCTION_FOUND("messages.deduction_found"),
    DEDUCTION_LIST_FETCHED("messages.deduction_list_fetched"),
    DEDUCTION_LIST_PAGED("messages.deduction_list_paged"),
    DEDUCTION_DELETED_SUCCESSFULLY("messages.deduction_deleted_successfully"),
    TAX_SLAB_NOT_FOUND("messages.tax_slab_not_found"),
    TAX_SLAB_FOUND("messages.tax_slab_found"),
    TAX_SLAB_LIST_FETCHED("messages.tax_slab_list_fetched"),
    TAX_SLAB_LIST_PAGED("messages.tax_slab_list_paged"),
    TAX_SLAB_DELETED_SUCCESSFULLY("messages.tax_slab_deleted_successfully"),
    TAX_SLAB_ALREADY_EXISTS("messages.tax_slab_already_exists"),
    TAX_SLAB_CREATED_SUCCESSFULLY("messages.tax_slab_created_successfully"),
    EMPLOYEE_DEDUCTION_CREATED_SUCCESSFULLY("messages.employee_deduction_created_successfully"),
    EMPLOYEE_ALLOWANCE_NOT_FOUND("messages.employee_allowance_not_found"),
    MESSAGE_ALLOWANCES_NOT_FOUND("messages.message_allowances_not_found"),
    MESSAGE_EMPLOYEE_ALLOWANCE_RETRIEVED_SUCCESSFULLY("messages.message_employee_allowance_retrieved_successfully"),
    MESSAGE_DEDUCTIONS_NOT_FOUND("messages.message_deductions_not_found"),
    MESSAGE_EMPLOYEE_DEDUCTION_RETRIEVED_SUCCESSFULLY("messages.message_employee_deduction_retrieved_successfully"),
    EMPLOYEE_SALARY_STRUCTURE_ALREADY_EXISTS("messages.employee_salary_structure_already_exists"),
    SALARY_STRUCTURE_CREATED_SUCCESSFULLY("messages.salary_structure_created_successfully"),
    SALARY_STRUCTURES_RETRIEVED_SUCCESSFULLY("messages.salary_structures_retrieved_successfully"),
    SALARY_STRUCTURE_NOT_FOUND("messages.salary_structure_not_found"),
    PAYROLL_CREATED_SUCCESSFULLY("messages.payroll_created_successfully"),
    PAYROLL_DETAILS_RETRIEVED_SUCCESSFULLY("messages.payroll_details_retrieved_successfully"),
    PAYROLL_ALREADY_EXISTS("messages.payroll_already_exists"),;

    private String code;

    Messages(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
