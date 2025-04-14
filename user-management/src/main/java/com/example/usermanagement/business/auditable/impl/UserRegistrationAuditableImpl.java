package com.example.usermanagement.business.auditable.impl;

import com.example.usermanagement.business.auditable.api.UserRegistrationAuditable;
import com.example.usermanagement.domain.UserRegistration;
import com.example.usermanagement.domain.UserRole;
import com.example.usermanagement.repository.UserRegistrationRepository;
import java.util.Locale;

public class UserRegistrationAuditableImpl implements UserRegistrationAuditable {

    private final UserRegistrationRepository userRegistrationRepository;

    public UserRegistrationAuditableImpl(UserRegistrationRepository userRegistrationRepository) {
        this.userRegistrationRepository = userRegistrationRepository;
    }

    @Override
    public UserRegistration create(UserRegistration userRegistration, Locale locale, String username) {
        return userRegistrationRepository.save(userRegistration);
    }
}
