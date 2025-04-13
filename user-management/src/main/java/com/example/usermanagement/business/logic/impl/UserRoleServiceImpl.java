package com.example.usermanagement.business.logic.impl;

import com.example.usermanagement.business.auditable.api.UserRoleAuditable;
import com.example.usermanagement.business.logic.api.UserRoleService;
import com.example.usermanagement.business.validation.api.UserRoleValidator;
import com.example.usermanagement.domain.Status;
import com.example.usermanagement.domain.UserRole;
import com.example.usermanagement.repository.UserRoleRepository;
import com.example.usermanagement.utils.dto.UserRoleDto;
import com.example.usermanagement.utils.enums.Messages;
import com.example.usermanagement.utils.messages.api.MessageService;
import com.example.usermanagement.utils.requests.CreateUserRoleRequest;
import com.example.usermanagement.utils.responses.UserRoleResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final MessageService messageService;
    private final UserRoleValidator userRoleValidator;
    private final ModelMapper modelMapper;
    private final UserRoleAuditable userRoleAuditable;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, MessageService messageService,
                               UserRoleValidator userRoleValidator, ModelMapper modelMapper,
                               UserRoleAuditable userRoleAuditable) {
        this.userRoleRepository = userRoleRepository;
        this.messageService = messageService;
        this.userRoleValidator = userRoleValidator;
        this.modelMapper = modelMapper;
        this.userRoleAuditable = userRoleAuditable;
    }

    @Override
    public UserRoleResponse create(CreateUserRoleRequest createUserRoleRequest, Locale locale, String username) {
        String message = "";

        boolean isRequestValid = userRoleValidator.isRequestValid(createUserRoleRequest);

        if (!isRequestValid) {
            message = messageService.getMessage(Messages.INVALID_REQUEST.getCode(), new String[]{},
                    locale);

            return buildResponse(400, false, message, null, null,
                    null);
        }

        Optional<UserRole> userRoleRetrieved = userRoleRepository.findByNameAndStatus(createUserRoleRequest.getName(),
                Status.ACTIVE);

        if (userRoleRetrieved.isPresent()) {
            message = messageService.getMessage(Messages.ROLE_ALREADY_EXIST.getCode(), new String[]{},
                    locale);
            return buildResponse(400, false, message, null, null,
                    null);
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserRole userToBeSaved = modelMapper.map(createUserRoleRequest, UserRole.class);

        UserRole userRoleSaved = userRoleAuditable.create(userToBeSaved,locale,username);

        UserRoleDto userRoleDtoReturned = modelMapper.map(userRoleSaved, UserRoleDto.class);

        message = messageService.getMessage(Messages.USER_ROLE_CREATED_SUCCESSFULLY.getCode(), new String[]{},
                locale);

        return buildResponse(201, true, message, userRoleDtoReturned, null,
                null);
    }

    @Override
    public UserRoleResponse delete(Long id, Locale locale, String username) {
        return null;
    }

    @Override
    public UserRoleResponse findById(Long id, Locale locale, String username) {
        return null;
    }

    @Override
    public UserRoleResponse findAll(int page, int size, Locale locale) {
        String message = "";

        Pageable pageable = PageRequest.of(page, size);

        Page<UserRole> userRolePage = userRoleRepository.findByStatusNot(Status.DELETED, pageable);
        Page<UserRoleDto> userRoleDtoPage = convertUserRoleEntityToUserRoleDto(userRolePage);

        if (userRolePage.getContent().isEmpty()) {
            message = messageService.getMessage(Messages.MESSAGE_USER_ROLE_NOT_FOUND.getCode(),
                    new String[]{}, locale);
            return buildResponse(404, false, message, null, null,
                    null);
        }

        message = messageService.getMessage(Messages.MESSAGE_USER_ROLE_RETRIEVED_SUCCESSFULLY.getCode(),
                new String[]{}, locale);
        return buildResponse(200, true, message, null, null,
                userRoleDtoPage);
    }

    private Page<UserRoleDto> convertUserRoleEntityToUserRoleDto(Page<UserRole> userRolePage) {
        List<UserRoleDto> userRoleDtoList = new ArrayList<>();

        if (userRolePage != null) {
            for (UserRole userRole : userRolePage) {
                UserRoleDto userRoleDto = modelMapper.map(userRole, UserRoleDto.class);
                userRoleDtoList.add(userRoleDto);

            }
        }

        int page = userRolePage.getNumber();
        int size = userRolePage.getSize();

        size = size <= 0 ? 10 : size;

        Pageable pageable = PageRequest.of(page, size);

        return new PageImpl<UserRoleDto>(userRoleDtoList, pageable, userRolePage.getTotalElements());

    }


    private UserRoleResponse buildResponse(int statusCode, Boolean success, String message, UserRoleDto userRoleDto,
                                             List<UserRoleDto> userRoleDtoList, Page<UserRoleDto> userRoleDtoPage) {

        UserRoleResponse userRoleResponse = new UserRoleResponse();
        userRoleResponse.setUserRoleDto(userRoleDto);
        userRoleResponse.setUserRoleDtoList(userRoleDtoList);
        userRoleResponse.setUserRolesPage(userRoleDtoPage);
        userRoleResponse.setMessage(message);
        userRoleResponse.setSuccess(success);
        userRoleResponse.setStatusCode(statusCode);

        return userRoleResponse;
    }
}
