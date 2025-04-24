package com.example.employeemanagement.repository;

import com.example.employeemanagement.domain.Payroll;
import com.example.employeemanagement.domain.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PayrollRepository extends JpaRepository<Payroll, Long>,
        JpaSpecificationExecutor<Payroll> {

    Page<Payroll> findAllByStatusNot(Status status, Pageable pageable);
}
