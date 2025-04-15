package com.example.employeemanagement.business.config;

import com.example.employeemanagement.business.validation.api.DepartmentServiceValidator;
import com.example.employeemanagement.business.validation.api.EmployeeServiceValidator;
import com.example.employeemanagement.business.validation.impl.DepartmentServiceValidatorImpl;
import com.example.employeemanagement.business.validation.impl.EmployeeServiceValidatorImpl;
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
}
