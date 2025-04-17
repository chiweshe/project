package com.example.employeemanagement.service.processor.impl;

import com.example.employeemanagement.business.logic.api.DeductionService;
import com.example.employeemanagement.service.processor.api.DeductionProcessor;
import com.example.employeemanagement.utils.requests.CreateDeductionRequest;
import com.example.employeemanagement.utils.responses.AllowanceResponse;
import com.example.employeemanagement.utils.responses.DeductionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import java.util.Locale;

public class DeductionProcessorImpl implements DeductionProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DeductionService deductionService;

    public DeductionProcessorImpl(DeductionService deductionService) {
        this.deductionService = deductionService;
    }

    @Override
    public DeductionResponse create(CreateDeductionRequest createDeductionRequest, Locale locale, String username) {
        logger.info("Incoming request for saving allowance......");

        DeductionResponse deductionResponse = deductionService.create(createDeductionRequest, locale, username);
        return deductionResponse;
    }

    @Override
    public DeductionResponse findById(Long id, Locale locale) {
        logger.info("Incoming request for finding deduction by id: {}", id);
        return deductionService.findById(id, locale);    }

    @Override
    public DeductionResponse findAll(Locale locale) {
        logger.info("Incoming request for finding deduction as a list: {}", locale);
        return deductionService.findAll(locale);
    }

    @Override
    public DeductionResponse findAllAsPages(Pageable pageable, Locale locale) {
        logger.info("Incoming request for retrieving deductions as pages: {}", pageable);
        return deductionService.findAllAsPages(pageable, locale);
    }

    @Override
    public DeductionResponse delete(Long id, Locale locale) {
        logger.info("Incoming request for deleting deduction : {}", id);
        return deductionService.delete(id, locale);
    }

}
