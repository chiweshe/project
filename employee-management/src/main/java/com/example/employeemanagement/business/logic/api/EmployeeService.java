package com.example.employeemanagement.business.logic.api;

import com.example.employeemanagement.utils.requests.CreateEmployeeRequest;
import com.example.employeemanagement.utils.responses.EmployeeResponse;
import org.springframework.data.domain.Pageable;

import java.util.Locale;

public interface EmployeeService {

    EmployeeResponse create(CreateEmployeeRequest createEmployeeRequest, Locale locale, String username);

    EmployeeResponse findById(Long id, Locale locale);

    EmployeeResponse delete(Long id, Locale locale, String username);

    public EmployeeResponse findAllAsList(Locale locale) ;

    EmployeeResponse findAllAsPage(Pageable pageable, Locale locale);


    }
