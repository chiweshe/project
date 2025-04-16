package com.example.employeemanagement.utils.responses;

import com.example.employeemanagement.utils.dto.DeductionsDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Page;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeductionResponse extends CommonResponse{

    private DeductionsDto deductionsDto;
    private List<DeductionsDto> deductionsDtoList;
    private Page<DeductionsDto> deductionsDtoPage;

    public DeductionsDto getDeductionsDto() {
        return deductionsDto;
    }

    public void setDeductionsDto(DeductionsDto deductionsDto) {
        this.deductionsDto = deductionsDto;
    }

    public List<DeductionsDto> getDeductionsDtoList() {
        return deductionsDtoList;
    }

    public void setDeductionsDtoList(List<DeductionsDto> deductionsDtoList) {
        this.deductionsDtoList = deductionsDtoList;
    }

    public Page<DeductionsDto> getDeductionsDtoPage() {
        return deductionsDtoPage;
    }

    public void setDeductionsDtoPage(Page<DeductionsDto> deductionsDtoPage) {
        this.deductionsDtoPage = deductionsDtoPage;
    }
}
