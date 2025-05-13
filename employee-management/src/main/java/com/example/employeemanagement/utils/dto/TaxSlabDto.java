package com.example.employeemanagement.utils.dto;

import com.example.employeemanagement.domain.Status;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TaxSlabDto {

    private Long id;
    private BigDecimal lowerBound;
    private BigDecimal upperBound;
    private BigDecimal rate;
    private BigDecimal fixedAmount;
    private Status status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateLastModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(LocalDateTime dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public BigDecimal getFixedAmount() {
        return fixedAmount;
    }

    public void setFixedAmount(BigDecimal fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    @Override
    public String toString() {
        return "TaxSlabDto{" +
                "id=" + id +
                ", lowerBound=" + lowerBound +
                ", upperBound=" + upperBound +
                ", rate=" + rate +
                ", fixedAmount=" + fixedAmount +
                ", status=" + status +
                ", dateCreated=" + dateCreated +
                ", dateLastModified=" + dateLastModified +
                '}';
    }
}
