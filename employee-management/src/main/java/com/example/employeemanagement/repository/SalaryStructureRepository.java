package com.example.employeemanagement.repository;

import com.example.employeemanagement.domain.SalaryStructure;
import com.example.employeemanagement.domain.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface SalaryStructureRepository extends JpaRepository<SalaryStructure, Long>,
        JpaSpecificationExecutor<SalaryStructure> {
   
    Optional<SalaryStructure> findByEmployeeIdAndStatusNot(Long employeeId, Status status);

    Page<SalaryStructure> findAllByStatusNot(Status status, Pageable pageable);

    SalaryStructure findTopByEmployeeIdAndIsActiveTrueAndStatus(Long employeeId, Status status);

}
