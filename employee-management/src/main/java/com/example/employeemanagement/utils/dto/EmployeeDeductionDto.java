package com.example.employeemanagement.utils.dto;

import com.example.employeemanagement.domain.Deduction;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDeductionDto {

    private Long id;

    private Long employeeId;

    private String employeeName;

    private String deductionName;

    private BigDecimal amount;

    private String status;

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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeductionName() {
        return deductionName;
    }

    public void setDeductionName(String deductionName) {
        this.deductionName = deductionName;
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

    @Override
    public String toString() {
        return "EmployeeDeductionDto{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", deductionName='" + deductionName + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateLastModified=" + dateLastModified +
                '}';
    }
}
