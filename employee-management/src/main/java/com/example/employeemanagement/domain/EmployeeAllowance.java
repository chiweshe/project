package com.example.employeemanagement.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_allowance")
public class EmployeeAllowance {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long employeeId;
        Long allowanceId;
        BigDecimal amount;
        @Column(name = "status", nullable = false)
        @Enumerated(value = EnumType.STRING)
        private Status status;

        private LocalDateTime dateCreated;

        private LocalDateTime dateLastModified;

        public Long getEmployeeId() {
                return employeeId;
        }

        public void setEmployeeId(Long employeeId) {
                this.employeeId = employeeId;
        }

        public Long getAllowanceId() {
                return allowanceId;
        }

        public void setAllowanceId(Long allowanceId) {
                this.allowanceId = allowanceId;
        }

        public BigDecimal getAmount() {
                return amount;
        }

        public void setAmount(BigDecimal amount) {
                this.amount = amount;
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

        @PrePersist
        private void init(){
                dateCreated = LocalDateTime.now();
                status = Status.ACTIVE;

        }

        @PreUpdate
        public void update(){

                dateLastModified = LocalDateTime.now();
        }

        @Override
        public String toString() {
                return "EmployeeAllowance{" +
                        "employeeId=" + employeeId +
                        ", allowanceId=" + allowanceId +
                        ", amount=" + amount +
                        '}';
        }
}
