package com.example.usermanagement.repository;

import com.example.usermanagement.domain.Status;
import com.example.usermanagement.domain.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>, JpaSpecificationExecutor<UserRole> {
    Optional<UserRole> findByNameAndStatus(String name, Status status);

    List<UserRole> findByNameIn(List<String> roleNamesList);

    Optional<UserRole> findByIdAndStatus(Long id, Status status);

    Page<UserRole> findByStatusNot(Status status, Pageable pageable);
}
