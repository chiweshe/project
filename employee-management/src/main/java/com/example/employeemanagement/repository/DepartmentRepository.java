package com.example.employeemanagement.repository;

import com.example.employeemanagement.domain.Department;
import com.example.employeemanagement.domain.Status;

import java.util.Optional;

public interface DepartmentRepository {
    Optional<Department> findByNameAndStatusNot(String name, Status status);
}
