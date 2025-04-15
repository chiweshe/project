package com.example.employeemanagement.service.processor.api;

import com.example.employeemanagement.utils.requests.CreateEmployeeRequest;
import com.example.employeemanagement.utils.responses.EmployeeResponse;
import java.util.Locale;

public interface EmployeeProcessor {

    public EmployeeResponse save(CreateEmployeeRequest createEmployeeRequest, Locale locale, String username);

}
