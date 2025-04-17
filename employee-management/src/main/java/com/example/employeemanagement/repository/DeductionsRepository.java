package com.example.employeemanagement.repository;

import com.example.employeemanagement.domain.Deduction;
import com.example.employeemanagement.domain.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface DeductionsRepository extends JpaRepository<Deduction, Long>, JpaSpecificationExecutor<Deduction> {

    Optional<Deduction> findByNameAndStatusNot(String name, Status status);

    Optional<Deduction> findByIdAndStatusNot(Long id, Status status);
    List<Deduction> findAllByStatusNot(Status status);

    Page<Deduction> findAllByStatusNot(Status status, Pageable pageable);
}
