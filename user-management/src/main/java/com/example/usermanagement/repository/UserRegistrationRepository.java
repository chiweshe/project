package com.example.usermanagement.repository;

import com.example.usermanagement.domain.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long>,
        JpaSpecificationExecutor<UserRegistration> {
    List<UserRegistration> findByUsernameOrEmail(String username, String email);

    Optional<UserRegistration> findByUsername(String username);
}
