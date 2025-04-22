package com.example.employeemanagement.service.controller.frontend;

import com.example.employeemanagement.service.processor.api.PayrollProcessor;
import com.example.employeemanagement.utils.constants.Constants;
import com.example.employeemanagement.utils.requests.CreatePayrollRequest;
import com.example.employeemanagement.utils.responses.PayrollResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Locale;

@RestController
@CrossOrigin
@RequestMapping("/payroll-management/v1/payroll")
public class PayrollResource {

    private final PayrollProcessor payrollProcessor;

    public PayrollResource(PayrollProcessor payrollProcessor) {
        this.payrollProcessor = payrollProcessor;
    }

    @Operation(summary = "Create a payroll")
    @PostMapping(value = "")
    public PayrollResponse create(@Valid @RequestBody final CreatePayrollRequest createPayrollRequest,
                                  @Parameter(name = "Authorization", in = ParameterIn.HEADER,
                                                  description = "Bearer token", required = true)
                                          String authenticationToken,
                                  @Parameter(description = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                          @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                  defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return payrollProcessor.createPayroll(createPayrollRequest,  locale, authenticationToken);
    }
}
