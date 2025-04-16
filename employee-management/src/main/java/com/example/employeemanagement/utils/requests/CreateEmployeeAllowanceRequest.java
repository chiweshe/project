package com.example.employeemanagement.utils.requests;

import java.math.BigDecimal;

public class CreateEmployeeAllowanceRequest {

    Long employeeId;
    Long allowanceId;
    BigDecimal amount;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getAllowanceId() {
        return allowanceId;
    }

    public void setAllowanceId(Long allowanceId) {
        this.allowanceId = allowanceId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CreateEmployeeAllowanceRequest{" +
                "employeeId=" + employeeId +
                ", allowanceId=" + allowanceId +
                ", amount=" + amount +
                '}';
    }
}
