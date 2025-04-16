package com.example.employeemanagement.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_deduction")
public class EmployeeDeduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long employeeId;
    Long deductionId;
    BigDecimal amount;
    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    private LocalDateTime dateCreated;

    private LocalDateTime dateLastModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getDeductionId() {
        return deductionId;
    }

    public void setDeductionId(Long deductionId) {
        this.deductionId = deductionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
        return "EmployeeDeduction{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", deductionId=" + deductionId +
                ", amount=" + amount +
                '}';
    }
}
