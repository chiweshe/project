package com.example.usermanagement.business.config;

import com.example.usermanagement.business.auditable.api.UserGroupAuditable;
import com.example.usermanagement.business.auditable.api.UserRegistrationAuditable;
import com.example.usermanagement.business.auditable.api.UserRoleAuditable;
import com.example.usermanagement.business.auditable.impl.UserGroupAuditableImpl;
import com.example.usermanagement.business.auditable.impl.UserRegistrationAuditableImpl;
import com.example.usermanagement.business.auditable.impl.UserRoleAuditableImpl;
import com.example.usermanagement.business.logic.api.UserGroupService;
import com.example.usermanagement.business.logic.api.UserRegistrationService;
import com.example.usermanagement.business.logic.api.UserRoleService;
import com.example.usermanagement.business.logic.api.UserSessionService;
import com.example.usermanagement.business.logic.impl.UserGroupServiceImpl;
import com.example.usermanagement.business.logic.impl.UserRegistrationServiceImpl;
import com.example.usermanagement.business.logic.impl.UserRoleServiceImpl;
import com.example.usermanagement.business.logic.impl.UserSessionServiceImpl;
import com.example.usermanagement.business.validation.api.UserGroupValidator;
import com.example.usermanagement.business.validation.api.UserRegistrationValidator;
import com.example.usermanagement.business.validation.api.UserRoleValidator;
import com.example.usermanagement.business.validation.api.UserSessionValidatorService;
import com.example.usermanagement.business.validation.impl.UserGroupValidatorImpl;
import com.example.usermanagement.business.validation.impl.UserRegistrationValidatorImpl;
import com.example.usermanagement.business.validation.impl.UserRoleValidatorImpl;
import com.example.usermanagement.business.validation.impl.UserSessionValidatorServiceImpl;
import com.example.usermanagement.repository.UserGroupRepository;
import com.example.usermanagement.repository.UserRegistrationRepository;
import com.example.usermanagement.repository.UserRoleRepository;
import com.example.usermanagement.repository.UsersSessionsRepository;
import com.example.usermanagement.repository.config.DataConfig;
import com.example.usermanagement.utils.config.UtilsConfig;
import com.example.usermanagement.utils.messages.api.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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

    @Bean
    public UserRegistrationService userRegistrationService(UserRegistrationRepository userRegistrationRepository,
                                                           MessageService messageService,
                                                           UserRegistrationValidator userRegistrationValidator,
                                                           ModelMapper modelMapper,
                                                           UserRegistrationAuditable userRegistrationAuditable,
                                                           UserGroupRepository userGroupRepository,
                                                           UserSessionService userSessionService) {
        return new UserRegistrationServiceImpl(userRegistrationRepository, messageService, userRegistrationValidator,
                modelMapper, userRegistrationAuditable, userGroupRepository,userSessionService ) {
        };
    }

    @Bean
    public UserSessionService userSessionService(MessageService messageService,
                                                 UsersSessionsRepository usersSessionsRepository,
                                                 ModelMapper modelMapper, UserSessionValidatorService userSessionValidatorService){
        return new UserSessionServiceImpl(messageService, usersSessionsRepository,modelMapper, userSessionValidatorService);
    }

    @Bean
    public UserSessionValidatorService userSessionValidatorService() {
        return new UserSessionValidatorServiceImpl();
    }
    @Bean
    public UserRegistrationValidator userRegistrationValidator(){
        return new UserRegistrationValidatorImpl();
    }

    @Bean
    public UserGroupAuditable userGroupAuditable(UserGroupRepository userGroupRepository) {
        return new UserGroupAuditableImpl(userGroupRepository);
    }

    @Bean
    public UserRegistrationAuditable userRegistrationAuditable(UserRegistrationRepository userRegistrationRepository) {
        return new UserRegistrationAuditableImpl(userRegistrationRepository);
    }


    @Bean
    public UserGroupValidator userGroupValidator() {
        return new UserGroupValidatorImpl();
    }


    @Bean
    public UserGroupService userGroupService(UserGroupRepository userGroupRepository,ModelMapper modelMapper,
                                             UserGroupValidator userGroupValidator,
                                             MessageService messageService,
                                             UserGroupAuditable userGroupAuditable,
                                             UserRoleRepository userRoleRepository) {
        return new UserGroupServiceImpl(userGroupRepository, modelMapper, userGroupValidator, messageService, userGroupAuditable,
                userRoleRepository );
    }
}
