package com.example.usermanagement.service.config;

import com.example.usermanagement.business.config.BusinessConfig;
import com.example.usermanagement.business.logic.api.UserGroupService;
import com.example.usermanagement.business.logic.api.UserRoleService;
import com.example.usermanagement.service.processor.api.UserGroupProcessor;
import com.example.usermanagement.service.processor.api.UserRoleProcessor;
import com.example.usermanagement.service.processor.impl.UserGroupProcessorImpl;
import com.example.usermanagement.service.processor.impl.UserRoleProcessorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({BusinessConfig.class})
public class ServiceConfig {

    @Bean
    public UserRoleProcessor userRoleProcessor(UserRoleService userRoleService) {
        return new UserRoleProcessorImpl(userRoleService);
    }

    @Bean
    public UserGroupProcessor userGroupProcessor(UserGroupService userGroupService) {
        return new UserGroupProcessorImpl(userGroupService);
    }

}
