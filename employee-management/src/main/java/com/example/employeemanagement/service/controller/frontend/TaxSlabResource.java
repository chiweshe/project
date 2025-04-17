package com.example.employeemanagement.service.controller.frontend;

import com.example.employeemanagement.service.processor.api.TaxSlabProcessor;
import com.example.employeemanagement.utils.constants.Constants;
import com.example.employeemanagement.utils.requests.CreateDeductionRequest;
import com.example.employeemanagement.utils.requests.CreateTaxSlabRequest;
import com.example.employeemanagement.utils.responses.DeductionResponse;
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
@RequestMapping("/payroll-management/v1/tax-slab")
public class TaxSlabResource {

    public TaxSlabResource(TaxSlabProcessor taxSlabProcessor) {
        this.taxSlabProcessor = taxSlabProcessor;
    }

    private final TaxSlabProcessor taxSlabProcessor;

    @Operation(summary = "Create a tax slab")
    @PostMapping(value = "")
    public TaxSlabResponse create(@Valid @RequestBody final CreateTaxSlabRequest createTaxSlabRequest,
                                  @Parameter(name = "Authorization", in = ParameterIn.HEADER,
                                            description = "Bearer token", required = true)
                                    String authenticationToken,
                                  @Parameter(description = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                    @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                            defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return taxSlabProcessor.create(createTaxSlabRequest,  locale, authenticationToken);
    }


    @Operation(summary = "Find tax slab by id")
    @GetMapping("/{id}")
    public TaxSlabResponse findById(@PathVariable Long id, @RequestHeader(value = Constants.LOCALE_LANGUAGE,
            defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {

        return taxSlabProcessor.findById(id, locale);
    }

    @Operation(summary = "Delete tax slab")
    @DeleteMapping("/{id}")
    public TaxSlabResponse delete(@PathVariable Long id, @Parameter(name = "Authorization",
                                            in = ParameterIn.HEADER, description = "Bearer token", required = true)
                                    @RequestHeader("Authorization") String authenticationToken,
                                    @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                            defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {

        return taxSlabProcessor.delete(id, locale);
    }

    @Operation(summary = "Find all tax slabs as list")
    @GetMapping("/all")
    public TaxSlabResponse findAll(@RequestHeader(value = Constants.LOCALE_LANGUAGE,
            defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {

        return taxSlabProcessor.findAll(locale);
    }

    @Operation(summary = "Find tax slab as pages")
    @GetMapping("/pages")
    public TaxSlabResponse findAllAsPages(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                    defaultValue = Constants.DEFAULT_LOCALE) Locale locale) {
        Pageable pageable = PageRequest.of(page, size);
        return taxSlabProcessor.findAllAsPages(pageable, locale);
    }
}
