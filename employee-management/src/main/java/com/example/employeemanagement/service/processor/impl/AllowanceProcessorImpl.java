package com.example.employeemanagement.service.processor.impl;

import com.example.employeemanagement.business.logic.api.AllowanceService;
import com.example.employeemanagement.service.processor.api.AllowanceProcessor;
import com.example.employeemanagement.utils.requests.CreateAllowanceRequest;
import com.example.employeemanagement.utils.responses.AllowanceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;

import java.util.Locale;

public class AllowanceProcessorImpl implements AllowanceProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AllowanceService allowanceService;

    public AllowanceProcessorImpl(AllowanceService allowanceService) {
        this.allowanceService = allowanceService;
    }

    @Override
    public AllowanceResponse create(CreateAllowanceRequest request, Locale locale, String username) {

        logger.info("Incoming request for saving allowance......");

        AllowanceResponse allowanceResponse = allowanceService.create(request, locale, username);
        return allowanceResponse;
    }

    @Override
    public AllowanceResponse findById(Long id, Locale locale) {
        logger.info("Incoming request for retrieving allowance by id {}", id);
        return allowanceService.findById(id, locale);
    }

    @Override
    public AllowanceResponse findAll(Locale locale) {
        logger.info("Incoming request for retrieving all allowance {}", locale);
        return allowanceService.findAll(locale);
    }

    @Override
    public AllowanceResponse findAllAsPages(Pageable pageable, Locale locale) {
        logger.info("Incoming request for retrieving allowance as pages {}", pageable);
        return allowanceService.findAllAsPages(pageable, locale);
    }

    @Override
    public AllowanceResponse delete(Long id, Locale locale) {
        logger.info("Incoming request for deleting allowance{}", id);
        return allowanceService.delete(id, locale);
    }
}
