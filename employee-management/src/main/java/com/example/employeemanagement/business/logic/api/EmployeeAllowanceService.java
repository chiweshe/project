package com.example.employeemanagement.business.logic.api;

import com.example.employeemanagement.utils.requests.CreateEmployeeAllowanceRequest;
import com.example.employeemanagement.utils.responses.EmployeeAllowanceResponse;
import java.util.Locale;

public interface EmployeeAllowanceService {

    EmployeeAllowanceResponse create(CreateEmployeeAllowanceRequest createEmployeeAllowanceRequest, Locale locale,
                                     String username);

    EmployeeAllowanceResponse findById(Long id, Locale locale);

    EmployeeAllowanceResponse findAll(Locale locale);

    EmployeeAllowanceResponse findByEmployeeId(Long employeeId, Locale locale);

    EmployeeAllowanceResponse delete(Long id, Locale locale, String username);

    EmployeeAllowanceResponse viewAsPages(int page, int size, Locale locale);
}
