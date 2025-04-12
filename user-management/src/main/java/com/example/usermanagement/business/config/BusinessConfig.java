package com.example.usermanagement.business.config;

import com.example.usermanagement.business.auditable.api.UserRoleAuditable;
import com.example.usermanagement.business.auditable.impl.UserRoleAuditableImpl;
import com.example.usermanagement.business.logic.api.UserRoleService;
import com.example.usermanagement.business.logic.impl.UserRoleServiceImpl;
import com.example.usermanagement.business.validation.api.UserRoleValidator;
import com.example.usermanagement.business.validation.impl.UserRoleValidatorImpl;
import com.example.usermanagement.repository.UserRoleRepository;
import com.example.usermanagement.repository.config.DataConfig;
import com.example.usermanagement.utils.config.UtilsConfig;
import com.example.usermanagement.utils.messages.api.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import java.io.IOException;

@Configuration
@Import({DataConfig.class, UtilsConfig.class})
public class BusinessConfig {

    @Bean
    public UserRoleAuditable userRoleAuditable(UserRoleRepository userRoleRepository) {
        return new UserRoleAuditableImpl(userRoleRepository);
    }

    @Bean
    public UserRoleValidator userRoleValidator() {
        return new UserRoleValidatorImpl();
    }

    @Bean
    public UserRoleService userRoleService(UserRoleRepository userRoleRepository, MessageService messageService,
                                           UserRoleValidator userRoleValidator, ModelMapper modelMapper,
                                           UserRoleAuditable userRoleAuditable) {
        return new UserRoleServiceImpl(userRoleRepository, messageService, userRoleValidator, modelMapper, userRoleAuditable );
    }
}
