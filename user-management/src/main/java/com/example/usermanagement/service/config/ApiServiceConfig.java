package com.example.usermanagement.service.config;

import com.example.usermanagement.business.config.BusinessConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({BusinessConfig.class})
public class ApiServiceConfig {
}
