package com.example.employeemanagement.utils.responses;

import com.example.employeemanagement.utils.dto.DeductionDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Page;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeductionResponse extends CommonResponse{

    private DeductionDto deductionDto;
    private List<DeductionDto> deductionDtoList;
    private Page<DeductionDto> deductionDtoPage;

    public DeductionDto getDeductionDto() {
        return deductionDto;
    }

    public void setDeductionDto(DeductionDto deductionDto) {
        this.deductionDto = deductionDto;
    }

    public List<DeductionDto> getDeductionDtoList() {
        return deductionDtoList;
    }

    public void setDeductionDtoList(List<DeductionDto> deductionDtoList) {
        this.deductionDtoList = deductionDtoList;
    }

    public Page<DeductionDto> getDeductionDtoPage() {
        return deductionDtoPage;
    }

    public void setDeductionDtoPage(Page<DeductionDto> deductionDtoPage) {
        this.deductionDtoPage = deductionDtoPage;
    }

    @Override
    public String toString() {
        return "DeductionResponse{" +
                "deductionDto=" + deductionDto +
                ", deductionDtoList=" + deductionDtoList +
                ", deductionDtoPage=" + deductionDtoPage +
                '}';
    }
}
