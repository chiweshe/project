package com.example.employeemanagement.utils.requests;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateSalaryStructureRequest {

    Long employeeId;
    BigDecimal basicSalary;
    BigDecimal bonus;
    BigDecimal otherAllowances;
    BigDecimal deductions;
    LocalDate effectiveFrom;
    Boolean isActive;

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

    @Override
    public String toString() {
        return "CreateSalaryStructureRequest{" +
                "employeeId=" + employeeId +
                ", basicSalary=" + basicSalary +
                ", bonus=" + bonus +
                ", otherAllowances=" + otherAllowances +
                ", deductions=" + deductions +
                ", effectiveFrom=" + effectiveFrom +
                ", isActive=" + isActive +
                '}';
    }
}
