package com.example.employeemanagement.repository;

import com.example.employeemanagement.domain.Deduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeductionsRepository extends JpaRepository<Deduction, Long>, JpaSpecificationExecutor<Deduction> {
}
