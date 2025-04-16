package com.example.employeemanagement.business.logic.api;

import com.example.employeemanagement.utils.requests.CreateDepartmentRequest;
import com.example.employeemanagement.utils.responses.DepartmentResponse;
import org.springframework.data.domain.Pageable;
import java.util.Locale;

public interface DepartmentService {

    public DepartmentResponse saveDepartment(CreateDepartmentRequest createDepartmentRequest, Locale locale, String username);

    DepartmentResponse findDepartmentById(Long id, Locale locale);

    DepartmentResponse deleteDepartmentById(Long id, Locale locale, String username);

    DepartmentResponse getAllDepartmentsAsList(Locale locale);

    DepartmentResponse getAllDepartmentsAsPage(Pageable pageable, Locale locale);
}
