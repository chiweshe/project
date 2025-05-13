package com.example.employeemanagement.business.validation.impl;

import com.example.employeemanagement.business.validation.api.EmployeeServiceValidator;
import com.example.employeemanagement.utils.requests.CreateEmployeeRequest;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;


public class EmployeeServiceValidatorImpl implements EmployeeServiceValidator {

    @Override
    public boolean isRequestValid(CreateEmployeeRequest request) {

        if (request == null) {
            return false;
        }

        if (StringUtils.isBlank(request.getFirstName())
                || StringUtils.isBlank(request.getLastName())
                || StringUtils.isBlank(request.getEmail())
                || StringUtils.isBlank(request.getPhone())
                || StringUtils.isBlank(request.getAddress())
                || StringUtils.isBlank(request.getEmployeeCode())
                || StringUtils.isBlank(request.getJobTitle())
                || StringUtils.isBlank(request.getEmploymentType())
                || StringUtils.isBlank(request.getWorkLocation())) {
            return false;
        }
        if (request.getBirthDate() == null || request.getHireDate() == null) {
            return false;
        }

        if (request.getBirthDate().isAfter(LocalDate.now().minusYears(16))) {
            return false;
        }

        if (request.getHireDate().isAfter(LocalDate.now())) {
            return false;
        }
        if (request.getDepartmentId() == null || request.getDepartmentId() <= 0) {
            return false;
        }

        return true;
    }

    public String getErrorMessage(CreateEmployeeRequest request) {
        if (request.getBirthDate().isAfter(LocalDate.now().minusYears(16))) {
            return "The employee must be at least 16 years old.";
        }

        if (request.getBirthDate().isAfter(LocalDate.now())) {
            return "The birth date cannot be in the future.";
        }

        if (request.getHireDate().isAfter(LocalDate.now())) {
            return "The hire date cannot be in the future.";
        }

        return null;
    }
}
