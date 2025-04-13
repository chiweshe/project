package com.example.usermanagement.business.auditable.impl;

import com.example.usermanagement.business.auditable.api.UserGroupAuditable;
import com.example.usermanagement.domain.UserGroup;
import com.example.usermanagement.repository.UserGroupRepository;
import java.util.Locale;

public class UserGroupAuditableImpl implements UserGroupAuditable {

    private final UserGroupRepository userGroupRepository;

    public UserGroupAuditableImpl(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    public UserGroup create(UserGroup userGroup, Locale locale, String username) {
        return userGroupRepository.save(userGroup);
    }

    @Override
    public UserGroup save(UserGroup userGroup, Locale locale, String username) {
        return userGroupRepository.save(userGroup);
    }

    @Override
    public UserGroup delete(UserGroup userGroup, Locale locale, String username) {
        return userGroupRepository.save(userGroup);
    }
}
