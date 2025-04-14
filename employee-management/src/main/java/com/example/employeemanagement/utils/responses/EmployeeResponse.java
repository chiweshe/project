package com.example.employeemanagement.utils.responses;

import com.example.employeemanagement.utils.dto.EmployeeDto;
import org.springframework.data.domain.Page;
import java.util.List;

public class EmployeeResponse extends CommonResponse {

    private EmployeeDto employeeDto;
    private List<EmployeeDto> employeeDtoList;
    private Page<EmployeeDto> employeeDtoPage;

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }

    public List<EmployeeDto> getEmployeeDtoList() {
        return employeeDtoList;
    }

    public void setEmployeeDtoList(List<EmployeeDto> employeeDtoList) {
        this.employeeDtoList = employeeDtoList;
    }

    public Page<EmployeeDto> getEmployeeDtoPage() {
        return employeeDtoPage;
    }

    public void setEmployeeDtoPage(Page<EmployeeDto> employeeDtoPage) {
        this.employeeDtoPage = employeeDtoPage;
    }

    @Override
    public String toString() {
        return "EmployeeResponse{" +
                "employeeDto=" + employeeDto +
                ", employeeDtoList=" + employeeDtoList +
                ", employeeDtoPage=" + employeeDtoPage +
                '}';
    }
}
