package com.example.employeemanagement.utils.requests;

public class CreateDepartmentRequest {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CreateDepartmentRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
