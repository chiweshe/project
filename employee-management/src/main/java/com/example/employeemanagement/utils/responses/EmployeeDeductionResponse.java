package com.example.employeemanagement.utils.responses;

import com.example.employeemanagement.utils.dto.EmployeeDeductionDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Page;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDeductionResponse extends CommonResponse{

    private EmployeeDeductionDto employeeDeductionDto;
    private List<EmployeeDeductionDto> employeeDeductionDtoList;
    private Page<EmployeeDeductionDto> employeeDeductionDtoPage;

    public EmployeeDeductionDto getEmployeeDeductionDto() {
        return employeeDeductionDto;
    }

    public void setEmployeeDeductionDto(EmployeeDeductionDto employeeDeductionDto) {
        this.employeeDeductionDto = employeeDeductionDto;
    }

    public List<EmployeeDeductionDto> getEmployeeDeductionDtoList() {
        return employeeDeductionDtoList;
    }

    public void setEmployeeDeductionDtoList(List<EmployeeDeductionDto> employeeDeductionDtoList) {
        this.employeeDeductionDtoList = employeeDeductionDtoList;
    }

    public Page<EmployeeDeductionDto> getEmployeeDeductionDtoPage() {
        return employeeDeductionDtoPage;
    }

    public void setEmployeeDeductionDtoPage(Page<EmployeeDeductionDto> employeeDeductionDtoPage) {
        this.employeeDeductionDtoPage = employeeDeductionDtoPage;
    }

    @Override
    public String toString() {
        return "EmployeeDeductionResponse{" +
                "employeeDeductionDto=" + employeeDeductionDto +
                ", employeeDeductionDtoList=" + employeeDeductionDtoList +
                ", employeeDeductionDtoPage=" + employeeDeductionDtoPage +
                '}';
    }
}
