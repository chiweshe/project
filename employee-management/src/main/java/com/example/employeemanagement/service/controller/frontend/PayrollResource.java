package com.example.employeemanagement.service.controller.frontend;

import com.example.employeemanagement.service.processor.api.PayrollProcessor;
import com.example.employeemanagement.utils.constants.Constants;
import com.example.employeemanagement.utils.requests.CreatePayrollRequest;
import com.example.employeemanagement.utils.responses.EmployeeResponse;
import com.example.employeemanagement.utils.responses.PayrollResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Find all payroll details as pages")
    @GetMapping("/pages")
    public PayrollResponse findAllAsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(value = Constants.LOCALE_LANGUAGE, defaultValue = Constants.DEFAULT_LOCALE) Locale locale) {
        Pageable pageable = PageRequest.of(page, size);
        return payrollProcessor.findAllAsPage(pageable, locale);
    }
}
