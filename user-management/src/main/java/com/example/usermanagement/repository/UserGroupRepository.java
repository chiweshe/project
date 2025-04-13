package com.example.usermanagement.repository;

import com.example.usermanagement.domain.Status;
import com.example.usermanagement.domain.UserGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long>, JpaSpecificationExecutor<UserGroup>{
    Optional<UserGroup> findByNameAndStatus(String name, Status status);

    Optional<UserGroup> findByIdAndStatus(Long id, Status status);

    Page<UserGroup> findByStatusNot(Status status, Pageable pageable);
}
