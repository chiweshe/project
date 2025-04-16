package com.example.employeemanagement.repository;

import com.example.employeemanagement.domain.EmployeeDeduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeDeductionRepository extends JpaRepository<EmployeeDeduction, Long>, JpaSpecificationExecutor<EmployeeDeduction> {
}
