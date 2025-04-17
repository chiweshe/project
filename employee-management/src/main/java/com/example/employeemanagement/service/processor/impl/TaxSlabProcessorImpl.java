package com.example.employeemanagement.service.processor.impl;

import com.example.employeemanagement.business.logic.api.TaxSlabService;
import com.example.employeemanagement.service.processor.api.TaxSlabProcessor;
import com.example.employeemanagement.utils.requests.CreateTaxSlabRequest;
import com.example.employeemanagement.utils.responses.TaxSlabResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import java.util.Locale;

public class TaxSlabProcessorImpl implements TaxSlabProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TaxSlabService taxSlabService;

    public TaxSlabProcessorImpl(TaxSlabService taxSlabService) {
        this.taxSlabService = taxSlabService;
    }

    @Override
    public TaxSlabResponse create(CreateTaxSlabRequest createTaxSlabRequest, Locale locale, String username) {
        logger.info("Incoming request for saving tax slab......");

        TaxSlabResponse taxSlabResponse = taxSlabService.create(createTaxSlabRequest, locale, username);
        return taxSlabResponse;
    }

    @Override
    public TaxSlabResponse findById(Long id, Locale locale) {
        logger.info("Incoming request for retrieving tax slab by id {}", id);
        return taxSlabService.findById(id, locale);
    }

    @Override
    public TaxSlabResponse findAll(Locale locale) {
        logger.info("Incoming request for retrieving tax slab as list {}", locale);
        return taxSlabService.findAll(locale);
    }

    @Override
    public TaxSlabResponse findAllAsPages(Pageable pageable, Locale locale) {
        logger.info("Incoming request for retrieving tax slab as pages {}", pageable);
        return taxSlabService.findAllAsPages(pageable, locale);
    }

    @Override
    public TaxSlabResponse delete(Long id, Locale locale) {
        logger.info("Incoming request for deleting tax slab by id {}", id);
        return taxSlabService.delete(id, locale);
    }
}
