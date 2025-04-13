package com.example.usermanagement.business.auditable.impl;

import com.example.usermanagement.business.auditable.api.UserRoleAuditable;
import com.example.usermanagement.domain.UserRole;
import com.example.usermanagement.repository.UserRoleRepository;
import java.util.Locale;

public class UserRoleAuditableImpl implements UserRoleAuditable {

    private final UserRoleRepository userRoleRepository;

    public UserRoleAuditableImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole create(UserRole userRole, Locale locale, String username) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public UserRole delete(UserRole userRole, Locale locale, String username) {
        return userRoleRepository.save(userRole);
    }
}
