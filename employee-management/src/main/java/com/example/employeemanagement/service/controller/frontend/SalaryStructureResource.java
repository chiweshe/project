package com.example.employeemanagement.service.controller.frontend;


import com.example.employeemanagement.service.processor.api.SalaryStructureProcessor;
import com.example.employeemanagement.utils.constants.Constants;
import com.example.employeemanagement.utils.requests.CreateEmployeeRequest;
import com.example.employeemanagement.utils.requests.CreateSalaryStructureRequest;
import com.example.employeemanagement.utils.responses.EmployeeResponse;
import com.example.employeemanagement.utils.responses.SalaryStructureResponse;
import com.example.employeemanagement.utils.responses.TaxSlabResponse;
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
@RequestMapping("/payroll-management/v1/salary-structure")
public class SalaryStructureResource {

    private final SalaryStructureProcessor salaryStructureProcessor;

    public SalaryStructureResource(SalaryStructureProcessor salaryStructureProcessor) {
        this.salaryStructureProcessor = salaryStructureProcessor;
    }


    @Operation(summary = "Create a salary structure")
    @PostMapping(value = "")
    public SalaryStructureResponse create(@Valid @RequestBody final CreateSalaryStructureRequest createSalaryStructureRequest,
                                                  @Parameter(name = "Authorization", in = ParameterIn.HEADER,
                                                   description = "Bearer token", required = true)
                                           String authenticationToken,
                                                  @Parameter(description = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                           @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                   defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return salaryStructureProcessor.create(createSalaryStructureRequest,  locale, authenticationToken);
    }

    @Operation(summary = "Find salary structure(s) as pages")
    @GetMapping("/pages")
    public SalaryStructureResponse findAllAsPages(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                  defaultValue = Constants.DEFAULT_LOCALE) Locale locale) {
        Pageable pageable = PageRequest.of(page, size);
        return salaryStructureProcessor.findAllAsPage(pageable, locale);
    }
}
