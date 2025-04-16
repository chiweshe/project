package com.example.employeemanagement.repository;

import com.example.employeemanagement.domain.Allowance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AllowanceRepository  extends JpaRepository<Allowance, Long>, JpaSpecificationExecutor<Allowance> {
}
