package com.example.usermanagement.service.processor.api;

import com.example.usermanagement.utils.requests.CreateUserRoleRequest;
import com.example.usermanagement.utils.responses.UserRoleResponse;
import java.util.Locale;

public interface UserRoleProcessor {

    UserRoleResponse create(CreateUserRoleRequest createUserRoleRequest, Locale locale, String username);

}
