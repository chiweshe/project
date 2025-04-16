package com.example.employeemanagement.service.controller.frontend;

import com.example.employeemanagement.service.processor.api.DepartmentProcessor;
import com.example.employeemanagement.utils.constants.Constants;
import com.example.employeemanagement.utils.requests.CreateDepartmentRequest;
import com.example.employeemanagement.utils.responses.DepartmentResponse;
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
@RequestMapping("/payroll-management/v1/department")
public class DepartmentResource {

    private final DepartmentProcessor departmentProcessor;

    public DepartmentResource(DepartmentProcessor departmentProcessor) {
        this.departmentProcessor = departmentProcessor;
}

    @Operation(summary = "Create a department")
    @PostMapping(value = "")
    public DepartmentResponse createDepartment(@Valid @RequestBody final CreateDepartmentRequest createDepartmentRequest,
                                         @Parameter(name = "Authorization", in = ParameterIn.HEADER,
                                               description = "Bearer token", required = true)
                                       String authenticationToken,
                                         @Parameter(description = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                       @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                               defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return departmentProcessor.saveDepartment(createDepartmentRequest,  locale, authenticationToken);
    }

    @Operation(summary = "Find department by ID")
    @GetMapping("/{id}")
    public DepartmentResponse findDepartmentById(@PathVariable Long id,
                                                 @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                         defaultValue = Constants.DEFAULT_LOCALE) Locale locale) {
        return departmentProcessor.findDepartmentById(id, locale);
    }

    @Operation(summary = "Delete department by Id)")
    @DeleteMapping("/{id}")
    public DepartmentResponse deleteDepartmentById(@PathVariable Long id,
                                                   @Parameter(name = "Authorization",
                                                           in = ParameterIn.HEADER,
                                                           description = "Bearer token", required = true)
                                                   String authenticationToken,
                                                   @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                           defaultValue = Constants.DEFAULT_LOCALE) Locale locale) {
        return departmentProcessor.deleteDepartmentById(id, locale, authenticationToken);
    }

    @Operation(summary = "Get all departments as a list")
    @GetMapping("/list")
    public DepartmentResponse getAllDepartmentsAsList(
            @RequestHeader(value = Constants.LOCALE_LANGUAGE, defaultValue = Constants.DEFAULT_LOCALE) Locale locale) {
        return departmentProcessor.getAllDepartmentsAsList(locale);
    }

    @Operation(summary = "Get all departments as a page")
    @GetMapping("/page")
    public DepartmentResponse getAllDepartmentsAsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(value = Constants.LOCALE_LANGUAGE, defaultValue = Constants.DEFAULT_LOCALE) Locale locale) {
        Pageable pageable = PageRequest.of(page, size);
        return departmentProcessor.getAllDepartmentsAsPage(pageable, locale);
    }
}

