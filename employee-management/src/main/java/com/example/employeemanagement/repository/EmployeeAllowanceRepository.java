package com.example.employeemanagement.repository;

import com.example.employeemanagement.domain.EmployeeAllowance;
import com.example.employeemanagement.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface EmployeeAllowanceRepository extends JpaRepository<EmployeeAllowance, Long>, JpaSpecificationExecutor<EmployeeAllowance> {
    Optional<EmployeeAllowance> findByIdAndStatusNot(Long id, Status status);

    List<EmployeeAllowance> findByEmployeeIdAndStatusNot(Long employeeId, Status status);
}
