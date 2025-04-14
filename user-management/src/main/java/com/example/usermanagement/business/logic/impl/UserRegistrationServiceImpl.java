package com.example.usermanagement.business.logic.impl;

import com.example.usermanagement.business.auditable.api.UserRegistrationAuditable;
import com.example.usermanagement.business.logic.api.UserRegistrationService;
import com.example.usermanagement.business.logic.api.UserSessionService;
import com.example.usermanagement.business.validation.api.UserRegistrationValidator;
import com.example.usermanagement.domain.Status;
import com.example.usermanagement.domain.UserGroup;
import com.example.usermanagement.domain.UserRegistration;
import com.example.usermanagement.domain.UserRole;
import com.example.usermanagement.repository.UserGroupRepository;
import com.example.usermanagement.repository.UserRegistrationRepository;
import com.example.usermanagement.utils.constants.JwtTokenUtil;
import com.example.usermanagement.utils.constants.PasswordGenerator;
import com.example.usermanagement.utils.constants.PasswordUtil;
import com.example.usermanagement.utils.dto.UserGroupDto;
import com.example.usermanagement.utils.dto.UserRegistrationDto;
import com.example.usermanagement.utils.dto.UserRoleDto;
import com.example.usermanagement.utils.enums.Messages;
import com.example.usermanagement.utils.messages.api.MessageService;
import com.example.usermanagement.utils.requests.CreateSessionRequest;
import com.example.usermanagement.utils.requests.CreateUserRequest;
import com.example.usermanagement.utils.requests.EmailRequest;
import com.example.usermanagement.utils.responses.UserAuthenticationResponse;
import com.example.usermanagement.utils.responses.UserRegistrationResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRegistrationRepository userRegistrationRepository;
    private final MessageService messageService;
    private final UserRegistrationValidator userRegistrationValidator;
    private final ModelMapper modelMapper;
    private final UserRegistrationAuditable userRegistrationAuditable;
    private final UserGroupRepository userGroupRepository;
    private final UserSessionService userSessionService;
    private JwtTokenUtil jwtTokenUtil;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Value("${session.expireTime}")
    private long sessionExpiryTime;
    @Value("${admin.email.address}")
    private String adminEmail;
    @Value("${admin.default-user.username}")
    private String defaultUsername;
    @Value("${admin.default-user.password}")
    private String defaultPassword;
    @Value("${ldap.domain}")
    private String ldapDomain;

    public UserRegistrationServiceImpl(UserRegistrationRepository userRegistrationRepository,
                                       MessageService messageService, UserRegistrationValidator userRegistrationValidator,
                                       ModelMapper modelMapper, UserRegistrationAuditable userRegistrationAuditable,
                                       UserGroupRepository userGroupRepository, UserSessionService userSessionService) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.messageService = messageService;
        this.userRegistrationValidator = userRegistrationValidator;
        this.modelMapper = modelMapper;
        this.userRegistrationAuditable = userRegistrationAuditable;
        this.userGroupRepository = userGroupRepository;
        this.userSessionService = userSessionService;
    }

    @Override
    public UserRegistrationResponse registerUser(CreateUserRequest createUserRequest, Locale locale, String username) {

        String message = "";

        boolean isRequestValid = userRegistrationValidator.isRequestValid(createUserRequest);

        if (!isRequestValid) {
            message = messageService.getMessage(Messages.INVALID_REQUEST.getCode(), new String[]{},
                    locale);

            return buildResponse(400, false, message, null, null,
                    null);
        }

        if (checkIfUserAlreadyExists(createUserRequest.getUsername(), createUserRequest.getEmail())) {

            message = messageService.getMessage(Messages.MESSAGE_USER_EXISTS.getCode(),
                    new String[]{}, locale);
            return buildResponse(400, false, message, null, null,
                    null);
        }

        if (!checkIfUserGroupExists(createUserRequest.getUserGroupId())) {

            message = messageService.getMessage(Messages.MESSAGE_USER_GROUP_NOT_FOUND.getCode(),
                    new String[]{}, locale);
            return buildResponse(400, false, message, null, null,
                    null);

        }

        Optional<UserGroup> userGroup = userGroupRepository.findByIdAndStatus(createUserRequest.getUserGroupId(), Status.ACTIVE);

        UserRegistration userRegistrationToBeSaved = modelMapper.map(createUserRequest, UserRegistration.class);

        userRegistrationToBeSaved.setUserGroup(userGroup.get());

        String autoGeneratedPassword = PasswordGenerator.getCode();

        String hashedPassword = PasswordUtil.encrypt(autoGeneratedPassword);

        userRegistrationToBeSaved.setPassword(hashedPassword);

        userRegistrationToBeSaved.setChangePassword("Y");


        UserRegistration userRegistrationSaved = userRegistrationAuditable.create(userRegistrationToBeSaved, locale, username);

        UserRegistrationDto userRegistrationDtoReturned = modelMapper.map(userRegistrationSaved, UserRegistrationDto.class);

        userRegistrationDtoReturned.setPassword(null);

        UserGroupDto userGroupDto = modelMapper.map(userRegistrationSaved.getUserGroup(), UserGroupDto.class);

        Set<UserRole> userRole = userRegistrationSaved.getUserGroup().getUserRole();

        List<UserRoleDto> userRolesDto = convertUserRolesSetToUserRolesList(userRole);

        userGroupDto.setUserRolesDtos(userRolesDto);

        userRegistrationDtoReturned.setUserGroupDto(userGroupDto);

//        EmailRequest emailRequest = buildPasswordChangeRequest(userRegistrationSaved, autoGeneratedPassword, locale);
//
//        mailService.sendEmail(emailRequest, locale);

        message = messageService.getMessage(Messages.MESSAGE_USER_ACCOUNT_CREATION_SUCCESS.getCode(),
                new String[]{}, locale);

        return buildResponse(201, true, message, userRegistrationDtoReturned, null,
                null);
    }

    @Override
    public UserRegistrationResponse findByUsername(String username, Locale locale) {
        return null;
    }

    @Override
    public UserRegistrationResponse findByUsernameOrCreate(String username, Locale locale) {
        return null;
    }

    @Override
    public UserRegistrationResponse findByUserDetails(String userDetails, Locale locale) {
        return null;
    }

    @Override
    public UserRegistrationResponse findUserDetailsByToken(String token, Locale locale) {
        return null;
    }

    @Override
    public UserRegistrationResponse findByEntityStatus(int page, int size, String entityStatus, Locale locale) {
        return null;
    }

    @Override
    public UserRegistrationResponse findAll(int page, int size, Locale locale) {
        return null;
    }

    @Override
    public UserAuthenticationResponse findByUsernameAndPassword(String username, String password, Locale locale) {
        UserAuthenticationResponse userAuthenticationResponse;
        String message = "";

        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {

            message = messageService.getMessage(Messages.MESSAGE_USERNAME_PASSWORD_REQUIRED.getCode(),
                    new String[]{}, locale);

            return buildAuthenticationResponse (401, false, message, null);

        }

        String hashOfPasswordProvided = PasswordUtil.encrypt(password);

        Optional<UserRegistration> userWithProvidedUsername = userRegistrationRepository.findByUsername(username);

        if (!userWithProvidedUsername.isPresent()) {

            message = messageService.getMessage(Messages.MESSAGE_USERNAME_INVALID.getCode(),
                    new String[]{}, locale);

            return buildAuthenticationResponse (401, false, message, null);

        }

        Optional<UserRegistration> userWithProvidedCredentials = userRegistrationRepository.findByUsername(username);

        String storedPassword = userWithProvidedCredentials.get().getPassword();

        if (!hashOfPasswordProvided.equals(storedPassword)) {

            if (userWithProvidedUsername.get().getLoginAttempts() >= 3) {

                userWithProvidedUsername.get().setLocked("Y");
                userWithProvidedUsername.get().setStatus(Status.INACTIVE);

                UserRegistration userRegistrationLocked = userRegistrationAuditable.create(userWithProvidedUsername.get(), locale, username);

                message = messageService.getMessage(Messages.MESSAGE_ACCOUNT_LOCKED.getCode(),
                        new String[]{}, locale);

                return buildAuthenticationResponse (401, false, message, null);
            }

            if (userWithProvidedUsername.get().getLoginAttempts() < 3) {

                int existingLoginAttempts = userWithProvidedUsername.get().getLoginAttempts();
                int updatedLoginAttempts = ++existingLoginAttempts;

                userWithProvidedUsername.get().setLoginAttempts(updatedLoginAttempts);

                UserRegistration userLoginWithUpdateLoginAttempts =  userRegistrationAuditable.create(userWithProvidedUsername.get()
                        , locale, username);

                message = messageService.getMessage(Messages.MESSAGE_USERNAME_PASSWORD_WRONG.getCode(),
                        new String[]{}, locale);

                return buildAuthenticationResponse (401, false, message, null);

            }
        }

        if (userWithProvidedCredentials.get().getLocked().equals("Y")) {

            message = messageService.getMessage(Messages.MESSAGE_ACCOUNT_LOCKED.getCode(),
                    new String[]{}, locale);

            return buildAuthenticationResponse (401, false, message, null);

        }

        if (userWithProvidedCredentials.get().getStatus() == Status.INACTIVE ||
                userWithProvidedCredentials.get().getStatus() == Status.DELETED) {

            message = messageService.getMessage(Messages.MESSAGE_ACCOUNT_NOT_ACTIVE.getCode(),
                    new String[]{}, locale);

            return buildAuthenticationResponse (401, false, message, null);

        }

        userWithProvidedCredentials.get().setLoginAttempts(0);
        userWithProvidedCredentials.get().setDateLastLogin(LocalDateTime.now());

        UserRegistration userRegistration = userRegistrationAuditable.create(userWithProvidedCredentials.get(), locale, username);

        logger.info("user login   : {}", userRegistration.getUserGroup().getUserRole());

        UserRegistrationDto userRegistrationDtoDtoReturned = modelMapper.map(userRegistration, UserRegistrationDto.class);

        UserGroupDto userGroupDto = modelMapper.map(userRegistration.getUserGroup(), UserGroupDto.class);

        Set<UserRole> userRoles = userRegistration.getUserGroup().getUserRole();

        logger.info("user login   : {}", userRoles);

        List<UserRoleDto> userRolesDtos = convertUserRolesSetToUserRolesList(userRoles);

        userGroupDto.setUserRolesDtos(userRolesDtos);

        userRegistrationDtoDtoReturned.setUserGroupDto(userGroupDto);

        String issuedToken = jwtTokenUtil.generateUserLoginToken(userRegistrationDtoDtoReturned.getUsername(),
                userRegistrationDtoDtoReturned.getEmail());

        message = messageService.getMessage(Messages.MESSAGE_USER_ACCOUNT_SUCCESSFULLY_AUTHENTICATED.getCode(),
                new String[]{}, locale);

        userAuthenticationResponse =  buildAuthenticationResponse(200, true, message, userRegistrationDtoDtoReturned);

        userAuthenticationResponse.setToken(issuedToken);

        createSession(userAuthenticationResponse, locale);

        return userAuthenticationResponse;
    }


    @Override
    public UserRegistrationResponse changePassword(String username, String oldPassword, String newPassword, String confirmationPassword, Locale locale) {
        return null;
    }

    @Override
    public UserAuthenticationResponse resetPassword(String username, Locale locale) {
        return null;
    }

    private Boolean checkIfUserAlreadyExists(String username, String email) {

        List<UserRegistration> userRegistrations = userRegistrationRepository.findByUsernameOrEmail(username, email);

        if (userRegistrations.isEmpty()) {
            return false;
        }

        return true;
    }

    private List<UserRoleDto> convertUserRolesSetToUserRolesList(Set<UserRole> userRolesSet) {

        List<UserRole> userRolesList = new ArrayList<>();
        List<UserRoleDto> userRolesDtoList = new ArrayList<>();

        if (userRolesSet.isEmpty()) {
            return userRolesDtoList;
        }

        for (UserRole userRoles : userRolesSet) {
            userRolesList.add(userRoles);
        }

        userRolesDtoList = modelMapper.map(userRolesList, new TypeToken<List<UserRoleDto>>() {
        }.getType());

        return userRolesDtoList;

    }


    private Boolean checkIfUserGroupExists(Long id) {

        Optional<UserGroup> userGroup = userGroupRepository.findById(id);

        if (!userGroup.isPresent()) {
            return false;
        }

        return true;

    }

    private EmailRequest buildPasswordChangeRequest(UserRegistration userRegistration, String temporaryPassword, Locale locale) {

        String message = messageService.getMessage(Messages.MESSAGE_PASSWORD_CHANGE.getCode(),
                new String[]{userRegistration.getFirstName().trim() + " " + userRegistration.getLastName().trim(),
                        userRegistration.getUsername().trim(), temporaryPassword}, locale);

        String subject = messageService.getMessage(Messages.MESSAGE_ACCOUNT_CREATION_SUCCESS.getCode(),
                new String[]{userRegistration.getUsername(), temporaryPassword}, locale);

        List<String> emailList = new ArrayList<>();
        emailList.add(userRegistration.getEmail());

        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setFromEmail(adminEmail);
        emailRequest.setMessage(message);
        emailRequest.setEmailList(emailList);
        emailRequest.setSubject(subject);

        return emailRequest;

    }

    @Async
    public void createSession(UserAuthenticationResponse userAuthenticationResponse, Locale locale) {
        userSessionService.createSession(buildCreateSessionRequest(userAuthenticationResponse.getUserRegistrationDto().getUsername(),
                userAuthenticationResponse.getToken(), LocalDateTime.now().plusSeconds(sessionExpiryTime)), locale, "SYSTEM");
    }



    UserRegistrationResponse buildResponse(int StatusCode, boolean success, String message, UserRegistrationDto userRegistrationDto,
                                           List<UserRegistrationDto> userRegistrationDtoList, Page<UserRegistrationDto> userRegistrationDtoPage) {

        UserRegistrationResponse userRegistrationResponse = new UserRegistrationResponse();
        userRegistrationResponse.setStatusCode(StatusCode);
        userRegistrationResponse.setMessage(message);
        userRegistrationResponse.setSuccess(success);
        userRegistrationResponse.setUserRegistrationDtoList(userRegistrationDtoList);
        userRegistrationResponse.setUserRegistrationDto(userRegistrationDto);
        userRegistrationResponse.setUserRegistrationDtoPage(userRegistrationDtoPage);
        return userRegistrationResponse;
    }

    UserAuthenticationResponse buildAuthenticationResponse(int statusCode, boolean success, String message, UserRegistrationDto
            userRegistrationDto) {
        if (userRegistrationDto != null) {
            userRegistrationDto.setPassword(null);
        }
        UserAuthenticationResponse userAuthenticationResponse = new UserAuthenticationResponse();
        userAuthenticationResponse.setAuthenticated(success);
        userAuthenticationResponse.setMessage(message);
        userAuthenticationResponse.setStatusCode(statusCode);
        userAuthenticationResponse.setUserRegistrationDto(userRegistrationDto);

        return userAuthenticationResponse;
    }

    private CreateSessionRequest buildCreateSessionRequest(String username,  String token, LocalDateTime expiryTime){
        CreateSessionRequest createSessionRequest = new CreateSessionRequest();

        createSessionRequest.setUserName(username);
        createSessionRequest.setToken(token);
        createSessionRequest.setExpiryTime(expiryTime);

        return createSessionRequest;
    }
}

