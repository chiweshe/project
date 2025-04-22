package com.example.employeemanagement.utils.requests;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateSalaryStructureRequest {

    private Long employeeId;
    private BigDecimal basicSalary;
    private  BigDecimal bonus;
    private LocalDate effectiveFrom;
    private Boolean isActive;

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

                ", effectiveFrom=" + effectiveFrom +
                ", isActive=" + isActive +
                '}';
    }
}
