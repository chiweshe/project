package com.example.employeemanagement.service.processor.api;

import com.example.employeemanagement.utils.requests.CreateTaxSlabRequest;
import com.example.employeemanagement.utils.responses.TaxSlabResponse;
import org.springframework.data.domain.Pageable;
import java.util.Locale;

public interface TaxSlabProcessor {

    TaxSlabResponse create(CreateTaxSlabRequest createTaxSlabRequest, Locale locale, String username);

    TaxSlabResponse findById(Long id, Locale locale);
    TaxSlabResponse findAll(Locale locale);
    TaxSlabResponse findAllAsPages(Pageable pageable, Locale locale);
    TaxSlabResponse delete(Long id, Locale locale);
}
