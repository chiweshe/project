package com.example.employeemanagement.business.logic.api;

import com.example.employeemanagement.utils.requests.CreateDepartmentRequest;
import com.example.employeemanagement.utils.responses.DepartmentResponse;
import java.util.Locale;

public interface DepartmentService {

    public DepartmentResponse saveDepartment(CreateDepartmentRequest createDepartmentRequest, Locale locale, String username);
}
