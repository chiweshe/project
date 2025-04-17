package com.example.employeemanagement.service.processor.api;

import com.example.employeemanagement.utils.requests.CreateDeductionRequest;
import com.example.employeemanagement.utils.responses.DeductionResponse;
import org.springframework.data.domain.Pageable;
import java.util.Locale;

public interface DeductionProcessor {

    DeductionResponse create(CreateDeductionRequest createDeductionRequest, Locale locale, String username);
    DeductionResponse findById(Long id, Locale locale);
    DeductionResponse findAll(Locale locale);
    DeductionResponse findAllAsPages(Pageable pageable, Locale locale);
    DeductionResponse delete(Long id, Locale locale);
}
