package com.example.employeemanagement.business.logic.api;

import com.example.employeemanagement.utils.requests.CreatePayrollRequest;
import com.example.employeemanagement.utils.responses.PayrollResponse;
import org.springframework.data.domain.Pageable;

import java.util.Locale;

public interface PayrollService {

    public PayrollResponse createPayroll(CreatePayrollRequest createPayrollRequest, Locale locale, String username);
    public PayrollResponse findAllAsPage(Pageable pageable, Locale locale);


}
