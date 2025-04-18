package com.example.employeemanagement.repository.config;

import com.example.employeemanagement.domain.DomainMarkerInterface;
import com.example.employeemanagement.repository.RepositoryMarkerInterface;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {RepositoryMarkerInterface.class})
@EntityScan(basePackageClasses = {DomainMarkerInterface.class})
public class DataConfig {
}
