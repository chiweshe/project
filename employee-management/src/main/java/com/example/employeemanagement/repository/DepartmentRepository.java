package com.example.employeemanagement.repository;

import com.example.employeemanagement.domain.Department;
import com.example.employeemanagement.domain.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {

    Optional<Department> findByNameAndStatusNot(String name, Status status);

    Department save(Department department);

    Optional<Department> findByIdAndStatusNot(Long id, Status status);

    List<Department> findAllByStatusNot(Status status);

    Page<Department> findAllByStatusNot(Status status, Pageable pageable);
}
