package com.example.employeemanagement.service.processor.api;

import com.example.employeemanagement.utils.requests.CreatePayrollRequest;
import com.example.employeemanagement.utils.responses.PayrollResponse;
import java.util.Locale;

public interface PayrollProcessor {

    public PayrollResponse createPayroll(CreatePayrollRequest createPayrollRequest, Locale locale, String username);

}
