package com.example.employeemanagement.utils.responses;

import com.example.employeemanagement.utils.dto.AllowanceDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Page;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AllowanceResponse extends CommonResponse {

    private AllowanceDto allowance;
    private List<AllowanceDto> allowanceDtoList;
    private Page<AllowanceDto> allowanceDtoPage;

    public AllowanceDto getAllowance() {
        return allowance;
    }

    public void setAllowance(AllowanceDto allowance) {
        this.allowance = allowance;
    }

    public List<AllowanceDto> getAllowanceDtoList() {
        return allowanceDtoList;
    }

    public void setAllowanceDtoList(List<AllowanceDto> allowanceDtoList) {
        this.allowanceDtoList = allowanceDtoList;
    }

    public Page<AllowanceDto> getAllowanceDtoPage() {
        return allowanceDtoPage;
    }

    public void setAllowanceDtoPage(Page<AllowanceDto> allowanceDtoPage) {
        this.allowanceDtoPage = allowanceDtoPage;
    }

    @Override
    public String toString() {
        return "AllowanceResponse{" +
                "allowance=" + allowance +
                ", allowanceDtoList=" + allowanceDtoList +
                ", allowanceDtoPage=" + allowanceDtoPage +
                '}';
    }
}
