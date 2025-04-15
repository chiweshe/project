package com.example.employeemanagement.service.processor.api;

import com.example.employeemanagement.utils.requests.CreateDepartmentRequest;
import com.example.employeemanagement.utils.responses.DepartmentResponse;
import java.util.Locale;


public interface DepartmentProcessor {

    public DepartmentResponse saveDepartment(CreateDepartmentRequest createDepartmentRequest, Locale locale, String username);

}
