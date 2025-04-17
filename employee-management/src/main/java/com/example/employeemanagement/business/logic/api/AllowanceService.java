package com.example.employeemanagement.business.logic.api;

import com.example.employeemanagement.utils.requests.CreateAllowanceRequest;
import com.example.employeemanagement.utils.responses.AllowanceResponse;
import org.springframework.data.domain.Pageable;

import java.util.Locale;

public interface AllowanceService {

    AllowanceResponse create(CreateAllowanceRequest request, Locale locale, String username);
    AllowanceResponse findById(Long id, Locale locale);
    AllowanceResponse findAll(Locale locale);
    AllowanceResponse findAllAsPages(Pageable pageable, Locale locale);
    AllowanceResponse delete(Long id, Locale locale);
}
