package com.example.usermanagement.business.auditable.api;

import com.example.usermanagement.domain.UserGroup;
import java.util.Locale;

public interface UserGroupAuditable {

    UserGroup create(UserGroup userGroup, Locale locale, String username);
    UserGroup save(UserGroup userGroup, Locale locale, String username);
    UserGroup delete(UserGroup userGroup, Locale locale, String username);
}
