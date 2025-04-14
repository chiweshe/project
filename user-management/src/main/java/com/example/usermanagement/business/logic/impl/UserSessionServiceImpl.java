package com.example.usermanagement.business.logic.impl;

import com.example.usermanagement.business.logic.api.UserSessionService;
import com.example.usermanagement.business.validation.api.UserSessionValidatorService;
import com.example.usermanagement.domain.Sessions;
import com.example.usermanagement.domain.Status;
import com.example.usermanagement.repository.UsersSessionsRepository;
import com.example.usermanagement.utils.dto.UserSessionsDto;
import com.example.usermanagement.utils.enums.Messages;
import com.example.usermanagement.utils.messages.api.MessageService;
import com.example.usermanagement.utils.requests.CreateSessionRequest;
import com.example.usermanagement.utils.requests.LogoutRequest;
import com.example.usermanagement.utils.responses.UserSessionsResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UserSessionServiceImpl implements UserSessionService {

    private MessageService messageService;
    private UsersSessionsRepository usersSessionsRepository;
    private ModelMapper modelMapper;
    private UserSessionValidatorService userSessionValidatorService;

    public UserSessionServiceImpl(MessageService messageService, UsersSessionsRepository usersSessionsRepository,
                                  ModelMapper modelMapper, UserSessionValidatorService userSessionValidatorService) {
        this.messageService = messageService;
        this.usersSessionsRepository = usersSessionsRepository;
        this.modelMapper = modelMapper;
        this.userSessionValidatorService = userSessionValidatorService;
    }

    @Override
    public UserSessionsResponse checkSession(Long id, Locale locale) {
        String message = "";

        if (id == null || id == 0) {
            message = messageService.getMessage(Messages.MESSAGE_SESSION_INVALID_SEARCH_REQUEST.getCode(), new String[]
                    {}, locale);
            return buildUserSessionsResponse(400, false, message, null, null, null);
        }

        Optional<Sessions> userSessionsRetrieved = usersSessionsRepository.findById(id);

        if (userSessionsRetrieved.isEmpty()) {
            message = messageService.getMessage(Messages.MESSAGE_SESSIONS_NOT_FOUND.getCode(), new String[]{},
                    locale);
            return buildUserSessionsResponse(404, false, message, null, null, null);
        }

        UserSessionsDto userSessionsDto = modelMapper.map(userSessionsRetrieved.get(), UserSessionsDto.class);

        message = messageService.getMessage(Messages.MESSAGE_SESSIONS_RETRIEVED_SUCCESSFULLY.getCode(), new String[]{},
                locale);

        return buildUserSessionsResponse(200, true, message, userSessionsDto, null, null);
    }

    @Override
    public UserSessionsResponse checkSessionByUsernameAndRecordStatus(String username, Status recordStatus) {
        return null;
    }

    @Override
    public UserSessionsResponse logout(LogoutRequest logoutRequest, Locale locale) {

        boolean isRequestValid = userSessionValidatorService.isLogoutValid(logoutRequest);
        String message = "";

        if (!isRequestValid) {
            message = messageService.getMessage(Messages.MESSAGE_SESSION_INVALID_REQUEST.getCode(), new String[]{},
                    locale);

            return buildUserSessionsResponse(400, false, message, null, null, null);
        }

        List<Sessions> userSessionsRetrieved;

        if (logoutRequest.getAppCode() != null && !logoutRequest.getAppCode().trim().isEmpty()) {
            userSessionsRetrieved = usersSessionsRepository.findByUserNameAndUserClassificationAndApplicationId(
                    logoutRequest.getUsername(), logoutRequest.getClassification().toUpperCase(), logoutRequest.getAppCode());
        } else {
            userSessionsRetrieved = usersSessionsRepository.findByUserNameAndUserClassification(logoutRequest.getUsername(),
                    logoutRequest.getClassification().toUpperCase());
        }

        if (userSessionsRetrieved.size() > 0) {
            usersSessionsRepository.deleteAll(userSessionsRetrieved);

            message = messageService.getMessage(Messages.MESSAGE_SESSION_CLOSED_SUCCESSFULLY.getCode(), new String[]{},
                    locale);
            return buildUserSessionsResponse(200, true, message, null, null, null);
        } else {
            message = messageService.getMessage(Messages.MESSAGE_ERROR_OCCURED.getCode(), new String[]{},
                    locale);

            return buildUserSessionsResponse(400, false, message, null, null, null);

        }
    }

    @Override
    public UserSessionsResponse createSession(CreateSessionRequest createSessionRequest, Locale locale, String username) {
        boolean isRequestValid = userSessionValidatorService.isCreateSessionRequestValid(createSessionRequest);
        String message = "";

        if (!isRequestValid) {
            message = messageService.getMessage(Messages.MESSAGE_SESSION_INVALID_REQUEST.getCode(), new String[]{},
                    locale);

            return buildUserSessionsResponse(400, false, message, null, null, null);
        }

        List<Sessions> userSessionsRetrieved = usersSessionsRepository.findByUserNameAndUserClassification(createSessionRequest.getUserName(),
                createSessionRequest.getUserName());

        if (userSessionsRetrieved.size() > 0) {
            message = messageService.getMessage(Messages.MESSAGE_SESSION_EXISTS.getCode(), new String[]{},
                    locale);
            return buildUserSessionsResponse(400, false, message, null, null, null);
        }

        Sessions userSessionsToBeSaved = new Sessions();
        userSessionsToBeSaved.setUserName(createSessionRequest.getUserName());
        userSessionsToBeSaved.setToken(createSessionRequest.getToken());
        userSessionsToBeSaved.setExpiryTime(createSessionRequest.getExpiryTime());

        Sessions userSessionsSaved = usersSessionsRepository.save(userSessionsToBeSaved);

        UserSessionsDto userSessionsDtoReturned = modelMapper.map(userSessionsSaved, UserSessionsDto.class);


        message = messageService.getMessage(Messages.MESSAGE_SESSION_CREATED_SUCCESSFULLY.getCode(), new String[]{},
                locale);

        return buildUserSessionsResponse(201, true, message, userSessionsDtoReturned, null, null);

    }


    private UserSessionsResponse buildUserSessionsResponse(int statusCode, Boolean success, String message,
                                                           UserSessionsDto userSessionsDto, List<UserSessionsDto> userSessionsDtoList,
                                                           Page<UserSessionsDto> userSessionsDtoPage) {
        UserSessionsResponse userSessionsResponse = new UserSessionsResponse();
        userSessionsResponse.setSuccess(success);
        userSessionsResponse.setStatusCode(statusCode);
        userSessionsResponse.setMessage(message);
        userSessionsResponse.setUserSessionsDto(userSessionsDto);
        userSessionsResponse.setUserSessionsDtoList(userSessionsDtoList);
        userSessionsResponse.setUserSessionsDtoPage(userSessionsDtoPage);

        return userSessionsResponse;

    }
}
