package com.example.usermanagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;

public class Sessions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "user_classification", nullable = false)
    private String userClassification;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_last_modified")
    private LocalDateTime dateLastModified;

    @Column(name = "expiry_time", nullable = false)
    private LocalDateTime expiryTime;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "application_id")
    private String applicationId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserClassification() {
        return userClassification;
    }

    public void setUserClassification(String userClassification) {
        this.userClassification = userClassification;
    }

    @PrePersist
    public void setUp() {
        dateCreated = LocalDateTime.now();
        status = Status.ACTIVE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(LocalDateTime dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    @PreUpdate
    public void update() {
        dateLastModified = LocalDateTime.now();
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Sessions{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userClassification='" + userClassification + '\'' +
                ", status=" + status +
                ", dateCreated=" + dateCreated +
                ", dateLastModified=" + dateLastModified +
                ", expiryTime=" + expiryTime +
                ", token='" + token + '\'' +
                ", applicationId='" + applicationId + '\'' +
                '}';
    }
}


