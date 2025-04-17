package com.example.employeemanagement.utils.requests;

import java.math.BigDecimal;

public class CreateTaxSlabRequest {

    private BigDecimal lowerBound;
    private BigDecimal upperBound;
    private BigDecimal rate;

    public BigDecimal getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(BigDecimal lowerBound) {
        this.lowerBound = lowerBound;
    }

    public BigDecimal getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(BigDecimal upperBound) {
        this.upperBound = upperBound;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CreateTaxSlabRequest{" +
                "lowerBound=" + lowerBound +
                ", upperBound=" + upperBound +
                ", rate=" + rate +
                '}';
    }
}
