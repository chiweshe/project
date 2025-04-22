package com.example.employeemanagement.repository;

import com.example.employeemanagement.domain.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PayrollRepository extends JpaRepository<Payroll, Long>,
        JpaSpecificationExecutor<Payroll> {
}
