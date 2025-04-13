package com.example.usermanagement.business.logic.impl;

import com.example.usermanagement.business.auditable.api.UserGroupAuditable;
import com.example.usermanagement.business.logic.api.UserGroupService;
import com.example.usermanagement.business.validation.api.UserGroupValidator;
import com.example.usermanagement.domain.Status;
import com.example.usermanagement.domain.UserGroup;
import com.example.usermanagement.domain.UserRole;
import com.example.usermanagement.repository.UserGroupRepository;
import com.example.usermanagement.repository.UserRoleRepository;
import com.example.usermanagement.utils.dto.UserGroupDto;
import com.example.usermanagement.utils.dto.UserRoleDto;
import com.example.usermanagement.utils.enums.Messages;
import com.example.usermanagement.utils.messages.api.MessageService;
import com.example.usermanagement.utils.requests.CreateUserGroupRequest;
import com.example.usermanagement.utils.responses.UserGroupResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

public class UserGroupServiceImpl implements UserGroupService {

    private final UserGroupRepository userGroupRepository;
    private final ModelMapper modelMapper;
    private final UserGroupValidator userGroupValidator;
    private final MessageService messageService;
    private final UserGroupAuditable userGroupAuditable;
    private final UserRoleRepository userRoleRepository;

    public UserGroupServiceImpl(UserGroupRepository userGroupRepository, ModelMapper modelMapper,
                                UserGroupValidator userGroupValidator, MessageService messageService,
                                UserGroupAuditable userGroupAuditable, UserRoleRepository userRoleRepository) {
        this.userGroupRepository = userGroupRepository;
        this.modelMapper = modelMapper;
        this.userGroupValidator = userGroupValidator;
        this.messageService = messageService;
        this.userGroupAuditable = userGroupAuditable;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserGroupResponse create(CreateUserGroupRequest userGroupRequest, Locale locale, String username) {

        String message = "";

        boolean isRequestValid = userGroupValidator.isRequestValid(userGroupRequest);

        if (!isRequestValid) {
            message = messageService.getMessage(Messages.INVALID_REQUEST.getCode(), new String[]{},
                    locale);

            return buildResponse(400, false, message, null, null,
                    null);
        }

        Optional<UserGroup> UserGroupRetrieved = userGroupRepository.findByNameAndStatus(userGroupRequest.getName(),
                Status.ACTIVE);

        if (UserGroupRetrieved.isPresent()) {
            message = messageService.getMessage(Messages.GROUP_ALREADY_EXIST.getCode(), new String[]{},
                    locale);
            return buildResponse(400, false, message, null, null,
                    null);
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserGroup userGroupToBeSaved = modelMapper.map(userGroupRequest, UserGroup.class);

        UserGroup userGroupSaved = userGroupAuditable.create(userGroupToBeSaved,locale,username);

        UserGroupDto userGroupDtoReturned = modelMapper.map(userGroupSaved, UserGroupDto.class);

        message = messageService.getMessage(Messages.USER_GROUP_CREATED_SUCCESSFULLY.getCode(), new String[]{},
                locale);

        return buildResponse(201, true, message, userGroupDtoReturned, null,
                null);
    }

    @Override
    public UserGroupResponse assignUserRolesToGroup(Long userGroupId, List<Long> userRoleIds, Locale locale, String username) {
        String message;

        if (userGroupId == null || userGroupId == 0 || userRoleIds.isEmpty()) {
            message = messageService.getMessage(Messages.MESSAGE_INVALID_REQUEST.getCode(),
                    new String[]{}, locale);
            return buildResponse(400, false, message, null, null,
                    null);
        }

        Optional<UserGroup> userGroup =
                userGroupRepository.findByIdAndStatus(userGroupId, Status.ACTIVE);

        if (!userGroup.isPresent()) {
            message = messageService.getMessage(Messages.MESSAGE_USER_GROUP_NOT_FOUND.getCode(), new String[]{},
                    locale);
            return buildResponse(404, false, message, null, null,
                    null);
        }

        UserGroup userGroupToAssignUserRole = userGroup.get();

        Set<UserRole> userRoleSet = new HashSet<>();

        for (Long userRoleId : userRoleIds) {
            if (userRoleId != 0) {
                Optional<UserRole> userRole =
                        userRoleRepository.findByIdAndStatus(userRoleId, Status.ACTIVE);
                if (userRole.isPresent()) {
                    userRoleSet.add(userRole.get());
                }
            }

        }

        if (userRoleSet.isEmpty()) {
            message = messageService.getMessage(Messages.MESSAGE_USER_ROLE_NOT_FOUND.getCode(),
                    new String[]{}, locale);
            return buildResponse(404, false, message, null, null,
                    null);
        }

        userRoleSet.addAll(userGroupToAssignUserRole.getUserRole());
        userGroupToAssignUserRole.setUserRole(userRoleSet);

        UserGroup userGroupReturned = userGroupAuditable.save(userGroupToAssignUserRole, locale, username);

        UserGroupDto userGroupDtoReturned = modelMapper.map(userGroupReturned, UserGroupDto.class);
        List<UserRole> userRolesList = new ArrayList<>(userGroupReturned.getUserRole());
        List<UserRoleDto> userRoleDtoSet = modelMapper.map(userGroupReturned.getUserRole(),
                new TypeToken<List<UserRoleDto>>() {
                }.getType());

        userGroupDtoReturned.setUserRolesDtos(userRoleDtoSet);

        message = messageService.getMessage(Messages.MESSAGE_ROLE_ASSIGNMENT_SUCCESS.getCode(), new String[]{},
                locale);
        return buildResponse(201, true, message, userGroupDtoReturned, null, null);
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
    public UserGroupResponse findAll(int page, int size, Locale locale) {
        String message = "";

        Pageable pageable = PageRequest.of(page, size);

        Page<UserGroup> userGroupPage = userGroupRepository.findByStatusNot(Status.DELETED, pageable);
        Page<UserGroupDto> userGroupDtoPage = convertUserGroupEntityToUserGroupDto(userGroupPage);

        if (userGroupPage.getContent().isEmpty()) {
            message = messageService.getMessage(Messages.MESSAGE_USER_GROUP_NOT_FOUND.getCode(),
                    new String[]{}, locale);
            return buildResponse(404, false, message, null, null,
                    null);
        }

        message = messageService.getMessage(Messages.MESSAGE_USER_GROUP_RETRIEVED_SUCCESSFULLY.getCode(),
                new String[]{}, locale);
        return buildResponse(200, true, message, null, null,
                userGroupDtoPage);
    }

    private Page<UserGroupDto> convertUserGroupEntityToUserGroupDto(Page<UserGroup> userGroupPage) {
        List<UserGroupDto> userGroupDtoList = new ArrayList<>();

        if (userGroupPage != null) {
            for (UserGroup userGroup : userGroupPage) {
                UserGroupDto userGroupDto = modelMapper.map(userGroup, UserGroupDto.class);
                userGroupDtoList.add(userGroupDto);

            }
        }

        int page = userGroupPage.getNumber();
        int size = userGroupPage.getSize();

        size = size <= 0 ? 10 : size;

        Pageable pageable = PageRequest.of(page, size);

        return new PageImpl<UserGroupDto>(userGroupDtoList, pageable, userGroupPage.getTotalElements());

    }

    private UserGroupResponse buildResponse(int statusCode, Boolean success, String message, UserGroupDto userGroupDto,
                                           List<UserGroupDto> userGroupDtoList, Page<UserGroupDto> userGroupDtoPage) {

        UserGroupResponse userGroupResponse = new UserGroupResponse();
        userGroupResponse.setUserGroupDto(userGroupDto);
        userGroupResponse.setUserGroupDtoList(userGroupDtoList);
        userGroupResponse.setUserGroupDtoPage(userGroupDtoPage);
        userGroupResponse.setMessage(message);
        userGroupResponse.setSuccess(success);
        userGroupResponse.setStatusCode(statusCode);

        return userGroupResponse;
    }
}
