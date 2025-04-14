package com.example.usermanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_registration")
public class UserRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    private String mobileNumber;

    private String locked;

    private int loginAttempts;

    private String resetPassword;

    private String changePassword;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_group_id", referencedColumnName = "id")
    private UserGroup userGroup;

    private LocalDateTime dateCreated;

    private LocalDateTime dateLastModified;
    private LocalDateTime dateLastLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
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

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public String getResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(String resetPassword) {
        this.resetPassword = resetPassword;
    }

    public String getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(String changePassword) {
        this.changePassword = changePassword;
    }

    public LocalDateTime getDateLastLogin() {
        return dateLastLogin;
    }

    public void setDateLastLogin(LocalDateTime dateLastLogin) {
        this.dateLastLogin = dateLastLogin;
    }

    @PrePersist
    private void init(){
        dateCreated = LocalDateTime.now();
        status = Status.ACTIVE;
        locked = "N";
        loginAttempts = 0;
        resetPassword = "N";
        changePassword = "Y";

    }

    @PreUpdate
    public void update(){

        dateLastModified = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "UserRegistration{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", locked='" + locked + '\'' +
                ", loginAttempts=" + loginAttempts +
                ", resetPassword='" + resetPassword + '\'' +
                ", changePassword='" + changePassword + '\'' +
                ", status=" + status +
                ", userGroup=" + userGroup +
                ", dateCreated=" + dateCreated +
                ", dateLastModified=" + dateLastModified +
                ", dateLastLogin=" + dateLastLogin +
                '}';
    }
}
