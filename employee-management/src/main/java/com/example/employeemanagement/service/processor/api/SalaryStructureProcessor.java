package com.example.employeemanagement.service.processor.api;

import com.example.employeemanagement.utils.requests.CreateSalaryStructureRequest;
import com.example.employeemanagement.utils.responses.SalaryStructureResponse;
import org.springframework.data.domain.Pageable;
import java.util.Locale;

public interface SalaryStructureProcessor {

    public SalaryStructureResponse create(CreateSalaryStructureRequest createSalaryStructureRequest, Locale locale, String username);
    public SalaryStructureResponse findAllAsPage(Pageable pageable, Locale locale);
}
