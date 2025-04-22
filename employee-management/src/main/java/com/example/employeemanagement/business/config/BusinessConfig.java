package com.example.employeemanagement.business.config;

import com.example.employeemanagement.business.validation.api.*;
import com.example.employeemanagement.business.validation.impl.*;
import com.example.employeemanagement.repository.config.DataConfig;
import com.example.employeemanagement.utils.config.UtilsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataConfig.class, UtilsConfig.class})
public class BusinessConfig {

    @Bean
    public DepartmentServiceValidator departmentServiceValidator() {
        return new DepartmentServiceValidatorImpl();
    }

    @Bean
    public EmployeeServiceValidator employeeServiceValidator() {
        return new EmployeeServiceValidatorImpl();
    }

    @Bean
    public AllowanceServiceValidator allowanceServiceValidator() {
        return new AllowanceServiceValidatorImpl();
    }

    @Bean
    public DeductionServiceValidator deductionServiceValidator() {
        return new DeductionServiceValidatorImpl();
    }

    @Bean
    public TaxSlabServiceValidator taxSlabServiceValidator() {
        return new TaxSlabServiceValidatorImpl();
    }

    @Bean
    public EmployeeAllowanceServiceValidator employeeAllowanceServiceValidator() {
        return new EmployeeAllowanceServiceValidatorImpl();
    }

    @Bean
    public SalaryStructureServiceValidator salaryStructureServiceValidator(){
        return new SalaryStructureServiceValidatorImpl();
    }

    @Bean
    public EmployeeDeductionValidator employeeDeductionValidator() {
        return new EmployeeDeductionValidatorImpl();
    }
}
