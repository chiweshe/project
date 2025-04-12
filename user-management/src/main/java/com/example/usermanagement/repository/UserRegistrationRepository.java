package com.example.usermanagement.repository;

import com.example.usermanagement.domain.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long>,
        JpaSpecificationExecutor<UserRegistration> {
}
