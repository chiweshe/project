package com.example.usermanagement.service.config;

import com.example.usermanagement.business.config.BusinessConfig;
import com.example.usermanagement.business.logic.api.UserRoleService;
import com.example.usermanagement.business.logic.impl.UserRoleServiceImpl;
import com.example.usermanagement.service.processor.api.UserRoleProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({BusinessConfig.class})
public class ServiceConfig {

    @Bean
    public UserRoleProcessor userRoleProcessor(UserRoleService userRoleService) {
        return new UserRoleServiceImpl(userRoleService);
    }

}
