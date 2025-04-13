package com.example.usermanagement.service.processor.impl;

import com.example.usermanagement.business.logic.api.UserGroupService;
import com.example.usermanagement.service.processor.api.UserGroupProcessor;
import com.example.usermanagement.utils.requests.CreateUserGroupRequest;
import com.example.usermanagement.utils.responses.UserGroupResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Locale;

public class UserGroupProcessorImpl implements UserGroupProcessor {

    private final UserGroupService userGroupService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserGroupProcessorImpl(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @Override
    public UserGroupResponse create(CreateUserGroupRequest userGroupRequest, Locale locale, String username) {
        logger.info("Incoming request to create a user group : {}", userGroupRequest);
        UserGroupResponse userGroupResponse = userGroupService.create(userGroupRequest,locale, username);
        logger.info("Outgoing response for create a user group : {}", userGroupResponse);
        return userGroupResponse;
    }

    @Override
    public UserGroupResponse assignUserRolesToGroup(Long userGroupId, List<Long> userRoleIds, Locale locale, String username) {
        logger.info("Incoming request to assign existing roles to a group : {}", userGroupId);
        UserGroupResponse userGroupResponse = userGroupService.assignUserRolesToGroup(userGroupId, userRoleIds, locale, username);
        logger.info("Outgoing response for assigning user roles to a group: {}", userGroupResponse.toString());
        return userGroupResponse;
    }

    @Override
    public UserGroupResponse delete(Long id, Locale locale, String username) {
        return null;
    }

    @Override
    public UserGroupResponse findById(Long id, Locale locale, String username) {
        return null;
    }

    @Override
    public UserGroupResponse findAll(int page, int size, Locale locale, String username) {
        logger.info("Incoming request to retrieve user groups as pages");
        UserGroupResponse userGroupResponse = userGroupService.findAll(page,size,locale);
        logger.info("Outgoing response for retrieving user groups as pages : {}", userGroupResponse);
        return userGroupResponse;
    }
}
