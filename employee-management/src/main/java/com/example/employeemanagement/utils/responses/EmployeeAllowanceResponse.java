package com.example.employeemanagement.utils.responses;

import com.example.employeemanagement.utils.dto.EmployeeAllowanceDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Page;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeAllowanceResponse extends CommonResponse {

    private EmployeeAllowanceDto employeeAllowanceDto;
    private List<EmployeeAllowanceDto> employeeAllowanceDtoList;
    private Page<EmployeeAllowanceDto> employeeAllowanceDtoPage;

    public EmployeeAllowanceDto getEmployeeAllowanceDto() {
        return employeeAllowanceDto;
    }

    public void setEmployeeAllowanceDto(EmployeeAllowanceDto employeeAllowanceDto) {
        this.employeeAllowanceDto = employeeAllowanceDto;
    }

    public List<EmployeeAllowanceDto> getEmployeeAllowanceDtoList() {
        return employeeAllowanceDtoList;
    }

    public void setEmployeeAllowanceDtoList(List<EmployeeAllowanceDto> employeeAllowanceDtoList) {
        this.employeeAllowanceDtoList = employeeAllowanceDtoList;
    }

    public Page<EmployeeAllowanceDto> getEmployeeAllowanceDtoPage() {
        return employeeAllowanceDtoPage;
    }

    public void setEmployeeAllowanceDtoPage(Page<EmployeeAllowanceDto> employeeAllowanceDtoPage) {
        this.employeeAllowanceDtoPage = employeeAllowanceDtoPage;
    }

    @Override
    public String toString() {
        return "EmployeeAllowanceResponse{" +
                "employeeAllowanceDto=" + employeeAllowanceDto +
                ", employeeAllowanceDtoList=" + employeeAllowanceDtoList +
                ", employeeAllowanceDtoPage=" + employeeAllowanceDtoPage +
                '}';
    }
}
