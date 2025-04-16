package com.example.employeemanagement.utils.responses;

import com.example.employeemanagement.utils.dto.PayslipDto;
import org.springframework.data.domain.Page;
import java.util.List;

public class PayslipResponse extends CommonResponse {

    private PayslipDto payslipDto;
    private List<PayslipDto> payslipDtoList;
    private Page<PayslipDto> payslipDtoPage;

    public PayslipDto getPayslipDto() {
        return payslipDto;
    }

    public void setPayslipDto(PayslipDto payslipDto) {
        this.payslipDto = payslipDto;
    }

    public List<PayslipDto> getPayslipDtoList() {
        return payslipDtoList;
    }

    public void setPayslipDtoList(List<PayslipDto> payslipDtoList) {
        this.payslipDtoList = payslipDtoList;
    }

    public Page<PayslipDto> getPayslipDtoPage() {
        return payslipDtoPage;
    }

    public void setPayslipDtoPage(Page<PayslipDto> payslipDtoPage) {
        this.payslipDtoPage = payslipDtoPage;
    }

    @Override
    public String toString() {
        return "PayslipResponse{" +
                "payslipDto=" + payslipDto +
                ", payslipDtoList=" + payslipDtoList +
                ", payslipDtoPage=" + payslipDtoPage +
                '}';
    }

}
