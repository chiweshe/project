package com.example.usermanagement.service.processor.api;

import com.example.usermanagement.utils.requests.CreateUserRoleRequest;
import com.example.usermanagement.utils.responses.UserRoleResponse;
import java.util.Locale;

public interface UserRoleProcessor {

    public UserRoleResponse create(CreateUserRoleRequest createUserRoleRequest, Locale locale, String username);
    public UserRoleResponse delete(Long id, Locale locale, String username);
    public UserRoleResponse findById(Long id, Locale locale, String username);
    public UserRoleResponse findAll(int page, int size, Locale locale, String username);

}
