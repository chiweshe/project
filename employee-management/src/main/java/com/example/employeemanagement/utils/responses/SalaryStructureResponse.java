package com.example.employeemanagement.utils.responses;

import com.example.employeemanagement.utils.dto.SalaryStructureDto;
import org.springframework.data.domain.Page;
import java.util.List;

public class SalaryStructureResponse extends CommonResponse {

    private SalaryStructureDto salaryStructureDto;
    private List<SalaryStructureDto> salaryStructureDtoList;
    private Page<SalaryStructureDto> salaryStructureDtoPage;

    public SalaryStructureDto getSalaryStructureDto() {
        return salaryStructureDto;
    }

    public void setSalaryStructureDto(SalaryStructureDto salaryStructureDto) {
        this.salaryStructureDto = salaryStructureDto;
    }

    public List<SalaryStructureDto> getSalaryStructureDtoList() {
        return salaryStructureDtoList;
    }

    public void setSalaryStructureDtoList(List<SalaryStructureDto> salaryStructureDtoList) {
        this.salaryStructureDtoList = salaryStructureDtoList;
    }

    public Page<SalaryStructureDto> getSalaryStructureDtoPage() {
        return salaryStructureDtoPage;
    }

    public void setSalaryStructureDtoPage(Page<SalaryStructureDto> salaryStructureDtoPage) {
        this.salaryStructureDtoPage = salaryStructureDtoPage;
    }

    @Override
    public String toString() {
        return "SalaryStructureResponse{" +
                "salaryStructureDto=" + salaryStructureDto +
                ", salaryStructureDtoList=" + salaryStructureDtoList +
                ", salaryStructureDtoPage=" + salaryStructureDtoPage +
                '}';
    }
}
