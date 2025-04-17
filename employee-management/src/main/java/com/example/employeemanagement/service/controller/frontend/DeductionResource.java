package com.example.employeemanagement.service.controller.frontend;

import com.example.employeemanagement.service.processor.api.DeductionProcessor;
import com.example.employeemanagement.utils.constants.Constants;
import com.example.employeemanagement.utils.requests.CreateAllowanceRequest;
import com.example.employeemanagement.utils.requests.CreateDeductionRequest;
import com.example.employeemanagement.utils.responses.AllowanceResponse;
import com.example.employeemanagement.utils.responses.DeductionResponse;
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
@RequestMapping("/payroll-management/v1/deduction")
public class DeductionResource {

    private final DeductionProcessor deductionProcessor;

    public DeductionResource(DeductionProcessor deductionProcessor) {
        this.deductionProcessor = deductionProcessor;
    }

    @Operation(summary = "Create a deduction")
    @PostMapping(value = "")
    public DeductionResponse create(@Valid @RequestBody final CreateDeductionRequest createDeductionRequest,
                                    @Parameter(name = "Authorization", in = ParameterIn.HEADER,
                                            description = "Bearer token", required = true)
                                    String authenticationToken,
                                    @Parameter(description = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                    @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                            defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return deductionProcessor.create(createDeductionRequest,  locale, authenticationToken);
    }


    @Operation(summary = "Find deduction by id")
    @GetMapping("/{id}")
    public DeductionResponse findById(@PathVariable Long id, @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                              defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {

        return deductionProcessor.findById(id, locale);
    }

    @Operation(summary = "Delete deduction")
    @DeleteMapping("/{id}")
    public DeductionResponse delete(@PathVariable Long id, @Parameter(name = "Authorization",
                                            in = ParameterIn.HEADER, description = "Bearer token", required = true)
                                    @RequestHeader("Authorization") String authenticationToken,
                                    @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                            defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {

        return deductionProcessor.delete(id, locale);
    }

    @Operation(summary = "Find all deductions as list")
    @GetMapping("/all")
    public DeductionResponse findAll(@RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                    defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {

        return deductionProcessor.findAll(locale);
    }

    @Operation(summary = "Find deductions as pages")
    @GetMapping("/pages")
    public DeductionResponse findAllAsPages(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                              defaultValue = Constants.DEFAULT_LOCALE) Locale locale) {
                                                      Pageable pageable = PageRequest.of(page, size);
        return deductionProcessor.findAllAsPages(pageable, locale);
    }
}
