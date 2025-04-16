package com.example.employeemanagement.utils.requests;

public class CreatePayslipRequest {

    Long payrollId;
    String payslipUrl;

    public Long getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(Long payrollId) {
        this.payrollId = payrollId;
    }

    public String getPayslipUrl() {
        return payslipUrl;
    }

    public void setPayslipUrl(String payslipUrl) {
        this.payslipUrl = payslipUrl;
    }

    @Override
    public String toString() {
        return "CreatePayslipRequest{" +
                "payrollId=" + payrollId +
                ", payslipUrl='" + payslipUrl + '\'' +
                '}';
    }
}
