package com.example.employeemanagement.utils.config;

import com.example.employeemanagement.utils.messages.api.MessageService;
import com.example.employeemanagement.utils.messages.impl.MessageServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.modelmapper.ModelMapper;


public class UtilsConfig {

        @Bean(name = "customMessageSource")
        public MessageSource customMessageSource() {
            final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
            messageSource.setBasename("classpath:i18/messages");
            messageSource.setDefaultEncoding("UTF-8");
            return messageSource;
        }

        @Bean
        public MessageService messageService() {
            return new MessageServiceImpl(customMessageSource());
        }

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }