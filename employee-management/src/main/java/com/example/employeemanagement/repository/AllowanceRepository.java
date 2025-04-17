package com.example.employeemanagement.repository;

import com.example.employeemanagement.domain.Allowance;
import com.example.employeemanagement.domain.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface AllowanceRepository  extends JpaRepository<Allowance, Long>, JpaSpecificationExecutor<Allowance> {
    Optional<Allowance> findByNameAndStatusNot(String name, Status status);
    Optional<Allowance> findByIdAndStatusNot(Long id, Status status);
    List<Allowance> findAllByStatusNot(Status status);
    Page<Allowance> findAllByStatusNot(Status status, Pageable pageable);
}
