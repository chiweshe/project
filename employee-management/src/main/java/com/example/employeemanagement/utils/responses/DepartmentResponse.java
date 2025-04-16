package com.example.employeemanagement.utils.responses;

import com.example.employeemanagement.utils.dto.DepartmentDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Page;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentResponse extends CommonResponse {

    private DepartmentDto departmentDto;
    private List<DepartmentDto> departmentDtoList;
    private Page<DepartmentDto> departmentDtoPage;

    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    public void setDepartmentDto(DepartmentDto departmentDto) {
        this.departmentDto = departmentDto;
    }

    public List<DepartmentDto> getDepartmentDtoList() {
        return departmentDtoList;
    }

    public void setDepartmentDtoList(List<DepartmentDto> departmentDtoList) {
        this.departmentDtoList = departmentDtoList;
    }

    public Page<DepartmentDto> getDepartmentDtoPage() {
        return departmentDtoPage;
    }

    public void setDepartmentDtoPage(Page<DepartmentDto> departmentDtoPage) {
        this.departmentDtoPage = departmentDtoPage;
    }

    @Override
    public String toString() {
        return "DepartmentResponse{" +
                "departmentDto=" + departmentDto +
                ", departmentDtoList=" + departmentDtoList +
                ", departmentDtoPage=" + departmentDtoPage +
                '}';
    }
}
