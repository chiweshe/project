package com.example.employeemanagement.utils.responses;

import com.example.employeemanagement.utils.dto.PayrollDto;
import org.springframework.data.domain.Page;
import java.util.List;

public class PayrollResponse extends CommonResponse {

    private PayrollDto payrollDto;
    private List<PayrollDto> payrollDtoList;
    private Page<PayrollDto> payrollDtoPage;

    public PayrollDto getPayrollDto() {
        return payrollDto;
    }

    public void setPayrollDto(PayrollDto payrollDto) {
        this.payrollDto = payrollDto;
    }

    public List<PayrollDto> getPayrollDtoList() {
        return payrollDtoList;
    }

    public void setPayrollDtoList(List<PayrollDto> payrollDtoList) {
        this.payrollDtoList = payrollDtoList;
    }

    public Page<PayrollDto> getPayrollDtoPage() {
        return payrollDtoPage;
    }

    public void setPayrollDtoPage(Page<PayrollDto> payrollDtoPage) {
        this.payrollDtoPage = payrollDtoPage;
    }

    @Override
    public String toString() {
        return "PayrollResponse{" +
                "payrollDto=" + payrollDto +
                ", payrollDtoList=" + payrollDtoList +
                ", payrollDtoPage=" + payrollDtoPage +
                '}';
    }
}
