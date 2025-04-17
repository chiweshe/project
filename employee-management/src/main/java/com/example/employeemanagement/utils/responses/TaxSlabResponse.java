package com.example.employeemanagement.utils.responses;

import com.example.employeemanagement.utils.dto.TaxSlabDto;
import org.springframework.data.domain.Page;
import java.util.List;

public class TaxSlabResponse extends CommonResponse {

    private TaxSlabDto taxSlabDto;
    private List<TaxSlabDto> taxSlabDtoList;
    private Page<TaxSlabDto> taxSlabDtoPage;

    public TaxSlabDto getTaxSlabDto() {
        return taxSlabDto;
    }

    public void setTaxSlabDto(TaxSlabDto taxSlabDto) {
        this.taxSlabDto = taxSlabDto;
    }

    public List<TaxSlabDto> getTaxSlabDtoList() {
        return taxSlabDtoList;
    }

    public void setTaxSlabDtoList(List<TaxSlabDto> taxSlabDtoList) {
        this.taxSlabDtoList = taxSlabDtoList;
    }

    public Page<TaxSlabDto> getTaxSlabDtoPage() {
        return taxSlabDtoPage;
    }

    public void setTaxSlabDtoPage(Page<TaxSlabDto> taxSlabDtoPage) {
        this.taxSlabDtoPage = taxSlabDtoPage;
    }

    @Override
    public String toString() {
        return "TaxSlabResponse{" +
                "taxSlabDto=" + taxSlabDto +
                ", taxSlabDtoList=" + taxSlabDtoList +
                ", taxSlabDtoPage=" + taxSlabDtoPage +
                '}';
    }
}
