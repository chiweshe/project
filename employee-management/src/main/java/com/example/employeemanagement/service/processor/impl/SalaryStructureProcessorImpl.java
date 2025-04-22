package com.example.employeemanagement.service.processor.impl;

import com.example.employeemanagement.business.logic.api.SalaryStructureService;
import com.example.employeemanagement.service.processor.api.SalaryStructureProcessor;
import com.example.employeemanagement.utils.requests.CreateSalaryStructureRequest;
import com.example.employeemanagement.utils.responses.SalaryStructureResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import java.util.Locale;

public class SalaryStructureProcessorImpl implements SalaryStructureProcessor {

    private final SalaryStructureService salaryStructureService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public SalaryStructureProcessorImpl(SalaryStructureService salaryStructureService) {
        this.salaryStructureService = salaryStructureService;
    }

    @Override
    public SalaryStructureResponse create(CreateSalaryStructureRequest createSalaryStructureRequest, Locale locale, String username) {

        logger.info("Incoming request to save employee salary structure....");
        SalaryStructureResponse salaryStructureResponse = salaryStructureService.create(createSalaryStructureRequest, locale, username);
        return salaryStructureResponse;
    }

    @Override
    public SalaryStructureResponse findAllAsPage(Pageable pageable, Locale locale) {
        logger.info("Incoming request to fetch paginated salary structures list - Page: {}, Size: {}",pageable, locale);
        return salaryStructureService.findAllAsPage(pageable, locale);
    }
}
