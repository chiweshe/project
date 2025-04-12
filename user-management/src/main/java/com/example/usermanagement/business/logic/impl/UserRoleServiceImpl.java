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
