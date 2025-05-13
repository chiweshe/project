package com.example.employeemanagement.repository;

import com.example.employeemanagement.domain.EmployeeDeduction;
import com.example.employeemanagement.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface EmployeeDeductionRepository extends JpaRepository<EmployeeDeduction, Long>, JpaSpecificationExecutor<EmployeeDeduction> {
    List<EmployeeDeduction> findByEmployeeIdAndStatusNot(Long employeeId, Status status);

    Optional<EmployeeDeduction> findByEmployeeIdAndDeductionIdAndStatusNot(Long employeeId, Long deductionId, Status status);
}
