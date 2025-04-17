package com.example.employeemanagement.service.controller.frontend;


import com.example.employeemanagement.service.processor.api.AllowanceProcessor;
import com.example.employeemanagement.utils.constants.Constants;
import com.example.employeemanagement.utils.requests.CreateAllowanceRequest;
import com.example.employeemanagement.utils.responses.AllowanceResponse;
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
@RequestMapping("/payroll-management/v1/allowance")
public class AllowanceResource {

    private final AllowanceProcessor allowanceProcessor;

    public AllowanceResource(AllowanceProcessor allowanceProcessor) {
        this.allowanceProcessor = allowanceProcessor;
    }

    @Operation(summary = "Create a allowance")
    @PostMapping(value = "")
    public AllowanceResponse create(@Valid @RequestBody final CreateAllowanceRequest createAllowanceRequest,
                                            @Parameter(name = "Authorization", in = ParameterIn.HEADER,
                                                   description = "Bearer token", required = true)
                                           String authenticationToken,
                                            @Parameter(description = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                           @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                   defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return allowanceProcessor.create(createAllowanceRequest,  locale, authenticationToken);
    }

    @Operation(summary = "Find allowance by id")
    @GetMapping("/{id}")
    public AllowanceResponse findById(@PathVariable Long id,
                                      @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                      defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {

        return allowanceProcessor.findById(id, locale);
    }

    @Operation(summary = "Delete allowance")
    @DeleteMapping("/{id}")
    public AllowanceResponse delete(@PathVariable Long id, @Parameter(name = "Authorization",
                                                in = ParameterIn.HEADER, description = "Bearer token", required = true)
                                                @RequestHeader("Authorization") String authenticationToken,
                                                 @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                         defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {

        return allowanceProcessor.delete(id, locale);
    }

    @Operation(summary = "Find all allowances as list")
    @GetMapping("/all")
    public AllowanceResponse findAll(@RequestHeader(value = Constants.LOCALE_LANGUAGE,
            defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return allowanceProcessor.findAll(locale);
    }

    @Operation(summary = "Find allowances as pages")
    @GetMapping("/pages")
    public AllowanceResponse findAllAllowancesAsPages(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                              defaultValue = Constants.DEFAULT_LOCALE) Locale locale) {
        Pageable pageable = PageRequest.of(page, size);
        return allowanceProcessor.findAllAsPages(pageable, locale);
    }
}
