package com.example.employeemanagement.utils.requests;

import java.time.YearMonth;

public class CreatePayrollRequest {


    private Long employeeId;

    private YearMonth payrollMonth;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public YearMonth getPayrollMonth() {
        return payrollMonth;
    }


    @Override
    public String toString() {
        return "CreatePayrollRequest{" +
                "employeeId=" + employeeId +
                ", payrollMonth=" + payrollMonth +
                '}';
    }
}
