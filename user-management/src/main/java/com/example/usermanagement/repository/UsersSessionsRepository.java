package com.example.usermanagement.repository;

import com.example.usermanagement.domain.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UsersSessionsRepository extends JpaRepository<Sessions, Long>, JpaSpecificationExecutor<Sessions> {

    Optional<Sessions> findById(Long id);
    List<Sessions> findByUserNameAndUserClassificationAndApplicationId(String userId,
                                                                       String userClassification,
                                                                       String applicationId);
    List<Sessions> findByUserNameAndUserClassification(String userName, String userClassification);
    List<Sessions> findByUserNameAndUserClassificationAndRecordStatus(String userName, String userClassification, String recordStatus);
    List<Sessions> findAllByAndExpiryTimeIsAfter(LocalDateTime expiryTime);
}
