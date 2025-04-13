package com.example.usermanagement.business.logic.api;

import com.example.usermanagement.utils.requests.CreateUserGroupRequest;
import com.example.usermanagement.utils.responses.UserGroupResponse;
import java.util.List;
import java.util.Locale;

public interface UserGroupService {

    public UserGroupResponse create(CreateUserGroupRequest userGroupRequest, Locale locale, String username);
    public UserGroupResponse assignUserRolesToGroup(Long userGroupId, List<Long> userRoleIds, Locale locale, String username);
    public UserGroupResponse delete(Long id, Locale locale, String username);
    public UserGroupResponse findById(Long id, Locale locale, String username);
    public UserGroupResponse findAll(int page, int size, Locale locale);

}
