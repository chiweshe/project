package com.example.employeemanagement.service.controller.frontend;


import com.example.employeemanagement.service.processor.api.EmployeeProcessor;
import com.example.employeemanagement.utils.constants.Constants;
import com.example.employeemanagement.utils.requests.CreateEmployeeRequest;
import com.example.employeemanagement.utils.responses.DepartmentResponse;
import com.example.employeemanagement.utils.responses.EmployeeResponse;
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
@RequestMapping("/payroll-management/v1/employee")
public class EmployeeResource {

    private final EmployeeProcessor employeeProcessor;

    public EmployeeResource(EmployeeProcessor employeeProcessor) {

        this.employeeProcessor = employeeProcessor;
    }

    @Operation(summary = "Create a employee")
    @PostMapping(value = "")
    public EmployeeResponse createEmployee(@Valid @RequestBody final CreateEmployeeRequest createEmployeeRequest,
                                             @Parameter(name = "Authorization", in = ParameterIn.HEADER,
                                                       description = "Bearer token", required = true)
                                               String authenticationToken,
                                             @Parameter(description = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                               @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                       defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return employeeProcessor.save(createEmployeeRequest,  locale, authenticationToken);
    }

    @Operation(summary = "Find employee by ID")
    @GetMapping("/{id}")
    public EmployeeResponse findById(
            @PathVariable Long id,
            @RequestHeader(value = Constants.LOCALE_LANGUAGE, defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {

        return employeeProcessor.findById(id, locale);
    }

    @Operation(summary = "Delete employee")
    @DeleteMapping("/{id}")
    public EmployeeResponse deleteEmployee(
            @PathVariable Long id,
            @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Bearer token", required = true)
            @RequestHeader("Authorization") String authenticationToken,
            @RequestHeader(value = Constants.LOCALE_LANGUAGE, defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {

        return employeeProcessor.delete(id, locale, authenticationToken);
    }

    @Operation(summary = "Find all employees")
    @GetMapping("/all")
    public EmployeeResponse findAllEmployees(
            @RequestHeader(value = Constants.LOCALE_LANGUAGE, defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {

        return employeeProcessor.findAll(locale);
    }

    @Operation(summary = "Find all employees as pages")
    @GetMapping("/pages")
    public EmployeeResponse findAllEmployeesAsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(value = Constants.LOCALE_LANGUAGE, defaultValue = Constants.DEFAULT_LOCALE) Locale locale) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeProcessor.findAllAsPages(pageable, locale);
    }
}


