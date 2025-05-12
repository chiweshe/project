package com.example.employeemanagement.service.processor.api;

import com.example.employeemanagement.utils.requests.CreateBulkPayrollRequest;
import com.example.employeemanagement.utils.requests.CreatePayrollRequest;
import com.example.employeemanagement.utils.responses.PayrollResponse;
import org.springframework.data.domain.Pageable;

import java.time.YearMonth;
import java.util.Locale;

public interface PayrollProcessor {

    public PayrollResponse createPayroll(CreatePayrollRequest createPayrollRequest, Locale locale, String username);
    public PayrollResponse findAllAsPage(Pageable pageable, Locale locale);
    public PayrollResponse createBulkPayroll(CreateBulkPayrollRequest createBulkPayrollRequest, Locale locale, String username);



}
