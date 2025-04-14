package com.example.usermanagement.utils.dto;

import com.example.usermanagement.domain.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSessionsDto {
    private Long id;
    private String userName;
    private String userClassification;
    private Status entityStatus;
    private LocalDateTime dateCreated;
    private LocalDateTime dateLastModified;
    private String applicationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Status getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(Status entityStatus) {
        this.entityStatus = entityStatus;
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

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public String toString() {
        return "UserSessionsDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userClassification='" + userClassification + '\'' +
                ", entityStatus=" + entityStatus +
                ", dateCreated=" + dateCreated +
                ", dateLastModified=" + dateLastModified +
                ", applicationId='" + applicationId + '\'' +
                '}';
    }
}
