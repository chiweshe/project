package com.example.employeemanagement.repository;

import com.example.employeemanagement.domain.EmployeeAllowance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeAllowanceRepository extends JpaRepository<EmployeeAllowance, Long>, JpaSpecificationExecutor<EmployeeAllowance> {
}
