package com.example.usermanagement.utils.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegistrationDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String mobileNumber;
    private String status;
    private UserGroupDto userGroupDto;
    private String locked;
    private int loginAttempts;
    private String resetPassword;
    private String changePassword;
    private String password;
    private LocalDateTime dateCreated;
    private LocalDateTime dateLastModified;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserGroupDto getUserGroupDto() {
        return userGroupDto;
    }

    public void setUserGroupDto(UserGroupDto userGroupDto) {
        this.userGroupDto = userGroupDto;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRegistrationDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", status='" + status + '\'' +
                ", userGroupDto=" + userGroupDto +
                ", locked='" + locked + '\'' +
                ", loginAttempts=" + loginAttempts +
                ", resetPassword='" + resetPassword + '\'' +
                ", changePassword='" + changePassword + '\'' +
                ", password='" + password + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateLastModified=" + dateLastModified +
                '}';
    }
}
