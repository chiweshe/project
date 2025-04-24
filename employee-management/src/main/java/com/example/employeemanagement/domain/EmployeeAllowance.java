package com.example.employeemanagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_allowance")
public class EmployeeAllowance {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "employee_id", nullable = false)
        private Employee employee;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "allowance_id", nullable = false)
        private Allowance allowance;

        BigDecimal amount;

        @Column(name = "status", nullable = false)
        @Enumerated(value = EnumType.STRING)
        private Status status;

//        private String employeeName;
//
//        private String allowanceName;

        private LocalDateTime dateCreated;

        private LocalDateTime dateLastModified;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Employee getEmployee() {
                return employee;
        }

        public void setEmployee(Employee employee) {
                this.employee = employee;
        }

        public Allowance getAllowance() {
                return allowance;
        }

        public void setAllowance(Allowance allowance) {
                this.allowance = allowance;
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

//        public String getEmployeeName() {
//                return employeeName;
//        }
//
//        public void setEmployeeName(String employeeName) {
//                this.employeeName = employeeName;
//        }
//
//        public String getAllowanceName() {
//                return allowanceName;
//        }
//
//        public void setAllowanceName(String allowanceName) {
//                this.allowanceName = allowanceName;
//        }

        @PrePersist
        private void init() {
                dateCreated = LocalDateTime.now();
                status = Status.ACTIVE;

        }

        @PreUpdate
        public void update() {

                dateLastModified = LocalDateTime.now();
        }

        @Override
        public String toString() {
                return "EmployeeAllowance{" +
                        "id=" + id +
                        ", employee=" + employee +
                        ", allowance=" + allowance +
                        ", amount=" + amount +
                        ", status=" + status +
//                        ", employeeName='" + employeeName + '\'' +
//                        ", allowanceName='" + allowanceName + '\'' +
                        ", dateCreated=" + dateCreated +
                        ", dateLastModified=" + dateLastModified +
                        '}';
        }
}
