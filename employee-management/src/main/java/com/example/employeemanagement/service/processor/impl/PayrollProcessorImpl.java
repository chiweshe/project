package com.example.employeemanagement.service.processor.impl;

import com.example.employeemanagement.business.logic.api.PayrollService;
import com.example.employeemanagement.service.processor.api.PayrollProcessor;
import com.example.employeemanagement.utils.requests.CreatePayrollRequest;
import com.example.employeemanagement.utils.responses.PayrollResponse;
import com.example.employeemanagement.utils.responses.SalaryStructureResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class PayrollProcessorImpl implements PayrollProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PayrollService payrollService;


    public PayrollProcessorImpl(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @Override
    public PayrollResponse createPayroll(CreatePayrollRequest createPayrollRequest, Locale locale, String username) {

        logger.info("Incoming request to save payroll....");
        PayrollResponse payrollResponse = payrollService.createPayroll(createPayrollRequest, locale, username);

        return payrollResponse;
    }
}
