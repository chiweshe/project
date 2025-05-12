package com.example.employeemanagement.repository;

import com.example.employeemanagement.domain.Employee;
import com.example.employeemanagement.domain.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    Optional<Employee> findByEmployeeCodeAndStatusNot(String employeeCode, Status status);

    Optional<Employee> findByIdAndStatusNot(Long id, Status status);

    List<Employee> findByStatusNot(Status status);

    Page<Employee> findAllByStatusNot(Status status, Pageable pageable);

    Optional<Employee> findByEmployeeCode(String employeeCode);

    Optional<Employee> findByEmployeeCodeAndEmailAndPhone(String employeeCode, String email, String phone);

    List<Employee> findAllByStatusNot(Status status);
}
