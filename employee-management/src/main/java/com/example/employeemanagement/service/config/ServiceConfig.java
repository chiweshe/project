package com.example.employeemanagement.service.config;

import com.example.employeemanagement.business.config.BusinessConfig;
import com.example.employeemanagement.business.logic.api.*;
import com.example.employeemanagement.service.processor.api.*;
import com.example.employeemanagement.service.processor.impl.*;
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
    @Bean
    AllowanceProcessor allowanceProcessor(AllowanceService allowanceService) {
        return new AllowanceProcessorImpl(allowanceService);
    }

    @Bean
    DeductionProcessor deductionProcessor(DeductionService deductionService) {
        return new DeductionProcessorImpl(deductionService);
    }

    @Bean
    EmployeeDeductionProcessor employeeDeductionProcessor(EmployeeDeductionService employeeDeductionService) {
        return new EmployeeDeductionProcessorImpl(employeeDeductionService);
    }

    @Bean
    EmployeeAllowanceProcessor employeeAllowanceProcessor(EmployeeAllowanceService employeeAllowanceService) {
        return new EmployeeAllowanceProcessorImpl(employeeAllowanceService);
    }
    @Bean
    SalaryStructureProcessor salaryStructureProcessor(SalaryStructureService salaryStructureService) {
        return new SalaryStructureProcessorImpl(salaryStructureService);
    }

    @Bean
    PayrollProcessor payrollProcessor(PayrollService payrollService) {
        return new PayrollProcessorImpl(payrollService);
    }
//
//    @Bean
//    PayslipProcessor payslipProcessor(PayslipService payslipService) {
//        return  new PayslipProcessorImpl(payslipService);
//    }

    @Bean
    TaxSlabProcessor taxSlabProcessor(TaxSlabService taxSlabService) {
        return new TaxSlabProcessorImpl(taxSlabService);
    }
}
