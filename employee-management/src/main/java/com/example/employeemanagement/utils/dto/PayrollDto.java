package com.example.employeemanagement.utils.dto;

import com.example.employeemanagement.domain.EmployeeAllowance;
import com.example.employeemanagement.domain.EmployeeDeduction;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayrollDto {

    private Long employeeId;
    private String employeeName;
    private YearMonth payrollMonth;
    private BigDecimal basicSalary;
    private BigDecimal totalAllowances;
    private BigDecimal totalDeductions;
    private BigDecimal grossPay;
    private BigDecimal taxAmount;
    private BigDecimal netPay;
//    private List<EmployeeAllowance> allowances;
//    private List<EmployeeDeduction> deductions;

    private LocalDateTime generatedAt;
    private String status;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getNetPay() {
        return netPay;
    }

    public void setNetPay(BigDecimal netPay) {
        this.netPay = netPay;
    }

//    public List<EmployeeAllowance> getAllowances() {
//        return allowances;
//    }
//
//    public void setAllowances(List<EmployeeAllowance> allowances) {
//        this.allowances = allowances;
//    }
//
//    public List<EmployeeDeduction> getDeductions() {
//        return deductions;
//    }
//
//    public void setDeductions(List<EmployeeDeduction> deductions) {
//        this.deductions = deductions;
//    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PayrollDto{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", payrollMonth=" + payrollMonth +
                ", basicSalary=" + basicSalary +
                ", totalAllowances=" + totalAllowances +
                ", totalDeductions=" + totalDeductions +
                ", grossPay=" + grossPay +
                ", taxAmount=" + taxAmount +
                ", netPay=" + netPay +
//                ", allowances=" + allowances +
//                ", deductions=" + deductions +
                ", generatedAt=" + generatedAt +
                ", status='" + status + '\'' +
                '}';
    }
}

