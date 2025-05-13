package com.example.employeemanagement.business.validation.api;

import com.example.employeemanagement.utils.requests.CreateTaxSlabRequest;

public interface TaxSlabServiceValidator {

    boolean validate(CreateTaxSlabRequest request);

}
