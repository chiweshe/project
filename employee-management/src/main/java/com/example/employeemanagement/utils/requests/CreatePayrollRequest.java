package com.example.employeemanagement.utils.requests;

import java.math.BigDecimal;
import java.time.YearMonth;

public class CreatePayrollRequest {

    Long employeeId;
    YearMonth payrollMonth;
    BigDecimal basicSalary;
    BigDecimal totalAllowances;
    BigDecimal totalDeductions;
    BigDecimal grossPay;
    BigDecimal netPay;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public YearMonth getPayrollMonth() {
        return payrollMonth;
    }

    public void setPayrollMonth(YearMonth payrollMonth) {
        this.payrollMonth = payrollMonth;
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

    public BigDecimal getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(BigDecimal totalDeductions) {
        this.totalDeductions = totalDeductions;
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
        return "CreatePayrollRequest{" +
                "employeeId=" + employeeId +
                ", payrollMonth=" + payrollMonth +
                ", basicSalary=" + basicSalary +
                ", totalAllowances=" + totalAllowances +
                ", totalDeductions=" + totalDeductions +
                ", grossPay=" + grossPay +
                ", netPay=" + netPay +
                '}';
    }
}
