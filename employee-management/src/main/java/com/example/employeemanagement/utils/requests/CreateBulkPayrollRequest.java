package com.example.employeemanagement.utils.requests;

import java.time.YearMonth;

public class CreateBulkPayrollRequest {

    private YearMonth payrollMonth;

    public YearMonth getPayrollMonth() {
        return payrollMonth;
    }

    public void setPayrollMonth(YearMonth payrollMonth) {
        this.payrollMonth = payrollMonth;
    }

    @Override
    public String toString() {
        return "CreateBulkPayrollRequest{" +
                "payrollMonth=" + payrollMonth +
                '}';
    }
}
