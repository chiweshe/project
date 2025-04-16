package com.example.employeemanagement.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payslip")
public class Payslip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long payrollId;
    String payslipUrl;
    LocalDateTime generatedAt;
    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    private LocalDateTime dateCreated;

    private LocalDateTime dateLastModified;

    public Long getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(Long payrollId) {
        this.payrollId = payrollId;
    }

    public String getPayslipUrl() {
        return payslipUrl;
    }

    public void setPayslipUrl(String payslipUrl) {
        this.payslipUrl = payslipUrl;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    @PrePersist
    private void init(){
        dateCreated = LocalDateTime.now();
        status = Status.ACTIVE;

    }

    @PreUpdate
    public void update(){

        dateLastModified = LocalDateTime.now();
    }


    @Override
    public String toString() {
        return "Payslip{" +
                "payrollId=" + payrollId +
                ", payslipUrl='" + payslipUrl + '\'' +
                ", generatedAt=" + generatedAt +
                '}';
    }
}
