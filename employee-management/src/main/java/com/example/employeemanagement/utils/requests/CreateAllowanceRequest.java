package com.example.employeemanagement.utils.requests;

public class CreateAllowanceRequest {

    String name;
    String description;
    Boolean isTaxable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getTaxable() {
        return isTaxable;
    }

    public void setTaxable(Boolean taxable) {
        isTaxable = taxable;
    }

    @Override
    public String toString() {
        return "CreateAllowanceRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isTaxable=" + isTaxable +
                '}';
    }
}
