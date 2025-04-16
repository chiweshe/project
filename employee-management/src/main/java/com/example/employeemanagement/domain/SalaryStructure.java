package com.example.employeemanagement.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "salary_structure")
public class SalaryStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long employeeId;
    BigDecimal basicSalary;
    BigDecimal bonus;
    BigDecimal otherAllowances;
    BigDecimal deductions;
    LocalDate effectiveFrom;
    Boolean isActive;
    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    private LocalDateTime dateCreated;

    private LocalDateTime dateLastModified;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getOtherAllowances() {
        return otherAllowances;
    }

    public void setOtherAllowances(BigDecimal otherAllowances) {
        this.otherAllowances = otherAllowances;
    }

    public BigDecimal getDeductions() {
        return deductions;
    }

    public void setDeductions(BigDecimal deductions) {
        this.deductions = deductions;
    }

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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
        return "SalaryStructure{" +
                "employeeId=" + employeeId +
                ", basicSalary=" + basicSalary +
                ", bonus=" + bonus +
                ", otherAllowances=" + otherAllowances +
                ", deductions=" + deductions +
                ", effectiveFrom=" + effectiveFrom +
                ", isActive=" + isActive +
                ", status=" + status +
                ", dateCreated=" + dateCreated +
                ", dateLastModified=" + dateLastModified +
                '}';
    }
}
