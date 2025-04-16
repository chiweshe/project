package com.example.employeemanagement.repository;

import com.example.employeemanagement.domain.SalaryStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SalaryStructureRepository extends JpaRepository<SalaryStructure, Long>, JpaSpecificationExecutor<SalaryStructure> {
}
