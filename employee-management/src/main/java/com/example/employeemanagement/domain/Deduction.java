package com.example.employeemanagement.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "deduction")
public class Deduction {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        String name;
        String description;
        @Column(name = "status", nullable = false)
        @Enumerated(value = EnumType.STRING)
        private Status status;

        private LocalDateTime dateCreated;

        private LocalDateTime dateLastModified;

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
                return "Deduction{" +
                        "name='" + name + '\'' +
                        ", description='" + description + '\'' +
                        ", status=" + status +
                        ", dateCreated=" + dateCreated +
                        ", dateLastModified=" + dateLastModified +
                        '}';
        }
}
