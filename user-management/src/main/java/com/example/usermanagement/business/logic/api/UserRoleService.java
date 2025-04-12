package com.example.usermanagement.business.logic.api;

import com.example.usermanagement.utils.requests.CreateUserRoleRequest;
import com.example.usermanagement.utils.responses.UserRoleResponse;
import java.util.Locale;

public interface UserRoleService {

    UserRoleResponse create(CreateUserRoleRequest createUserRoleRequest, Locale locale, String username);

}
