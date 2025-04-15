package com.example.employeemanagement.business.logic.api;

import com.example.employeemanagement.utils.requests.CreateEmployeeRequest;
import com.example.employeemanagement.utils.responses.EmployeeResponse;
import java.util.Locale;

public interface EmployeeService {

    public EmployeeResponse create(CreateEmployeeRequest createEmployeeRequest, Locale locale, String username);
}
