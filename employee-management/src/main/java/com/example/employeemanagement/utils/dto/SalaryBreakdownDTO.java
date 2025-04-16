package com.example.employeemanagement.utils.dto;

import java.math.BigDecimal;

public class SalaryBreakdownDTO {

    private BigDecimal basicSalary;
    private BigDecimal totalAllowances;
    private BigDecimal totalDeductions;
    private BigDecimal grossPay;
    private BigDecimal netPay;

    public BigDecimal getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(BigDecimal totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getTotalAllowances() {
        return totalAllowances;
    }

    public void setTotalAllowances(BigDecimal totalAllowances) {
        this.totalAllowances = totalAllowances;
    }

    public BigDecimal getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(BigDecimal grossPay) {
        this.grossPay = grossPay;
    }

    public BigDecimal getNetPay() {
        return netPay;
    }

    public void setNetPay(BigDecimal netPay) {
        this.netPay = netPay;
    }

    @Override
    public String toString() {
        return "SalaryBreakdownDTO{" +
                "basicSalary=" + basicSalary +
                ", totalAllowances=" + totalAllowances +
                ", totalDeductions=" + totalDeductions +
                ", grossPay=" + grossPay +
                ", netPay=" + netPay +
                '}';
    }
}
