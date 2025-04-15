package com.example.employeemanagement.service.config;

import com.example.employeemanagement.business.config.BusinessConfig;
import com.example.employeemanagement.business.logic.api.DepartmentService;
import com.example.employeemanagement.business.logic.api.EmployeeService;
import com.example.employeemanagement.service.processor.api.DepartmentProcessor;
import com.example.employeemanagement.service.processor.api.EmployeeProcessor;
import com.example.employeemanagement.service.processor.impl.DepartmentProcessorImp;
import com.example.employeemanagement.service.processor.impl.EmployeeProcessorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({BusinessConfig.class})
public class ServiceConfig {

    @Bean
    public DepartmentProcessor departmentProcessor(DepartmentService departmentService) {
        return new DepartmentProcessorImp(departmentService);
    }

    @Bean
    public EmployeeProcessor employeeProcessor(EmployeeService employeeService) {
        return new EmployeeProcessorImpl(employeeService);
    }
}
