package com.example.employeemanagement.utils.requests;

import java.math.BigDecimal;

public class CreateEmployeeDeductionRequest {

    private Long employeeId;
    private Long deductionId;
    private BigDecimal amount;

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

    @Override
    public String toString() {
        return "CreateEmployeeDeductionRequest{" +
                "employeeId=" + employeeId +
                ", deductionId=" + deductionId +
                ", amount=" + amount +
                '}';
    }
}
