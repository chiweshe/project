package com.example.employeemanagement.service.controller.frontend;

import com.example.employeemanagement.business.logic.api.DepartmentService;
import com.example.employeemanagement.service.processor.api.DepartmentProcessor;
import com.example.employeemanagement.utils.constants.Constants;
import com.example.employeemanagement.utils.requests.CreateDepartmentRequest;
import com.example.employeemanagement.utils.responses.DepartmentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
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
}
