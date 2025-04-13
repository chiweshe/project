package com.example.usermanagement.business.auditable.api;

import com.example.usermanagement.domain.UserRole;
import java.util.Locale;

public interface UserRoleAuditable {

    public UserRole create(UserRole userRole, Locale locale, String username);
    public UserRole delete(UserRole userRole, Locale locale, String username);
}
