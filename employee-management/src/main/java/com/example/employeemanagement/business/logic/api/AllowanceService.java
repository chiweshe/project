package com.example.employeemanagement.business.logic.api;

import com.example.employeemanagement.utils.requests.CreateAllowanceRequest;
import com.example.employeemanagement.utils.responses.AllowanceResponse;
import java.util.Locale;

public interface AllowanceService {

    AllowanceResponse create(CreateAllowanceRequest request, Locale locale, String username);
}
