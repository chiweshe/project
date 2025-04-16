package com.example.employeemanagement.service.processor.api;

import com.example.employeemanagement.utils.requests.CreateEmployeeRequest;
import com.example.employeemanagement.utils.responses.EmployeeResponse;
import org.springframework.data.domain.Pageable;
import java.util.Locale;

public interface EmployeeProcessor {

    EmployeeResponse save(CreateEmployeeRequest createEmployeeRequest, Locale locale, String username);

    EmployeeResponse findById(Long id, Locale locale);

    EmployeeResponse delete(Long id, Locale locale, String username);

    EmployeeResponse findAll(Locale locale);

    EmployeeResponse findAllAsPages(Pageable pageable, Locale locale);

}
