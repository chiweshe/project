package com.example.usermanagement.business.auditable.api;

import com.example.usermanagement.domain.UserRegistration;
import java.util.Locale;

public interface UserRegistrationAuditable {

    public UserRegistration create(UserRegistration userRegistration, Locale locale, String username);
}
