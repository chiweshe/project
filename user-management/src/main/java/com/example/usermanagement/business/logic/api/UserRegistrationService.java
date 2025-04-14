package com.example.usermanagement.business.logic.api;

import com.example.usermanagement.utils.requests.CreateUserRequest;
import com.example.usermanagement.utils.responses.UserAuthenticationResponse;
import com.example.usermanagement.utils.responses.UserRegistrationResponse;
import java.util.Locale;

public interface UserRegistrationService {

    public UserRegistrationResponse registerUser(CreateUserRequest createUserRequest, Locale locale, String username);

    public UserRegistrationResponse findByUsername(String username, Locale locale);

    public UserRegistrationResponse findByUsernameOrCreate(String username, Locale locale);

    public UserRegistrationResponse findByUserDetails(String userDetails, Locale locale);

    public UserRegistrationResponse findUserDetailsByToken(String token, Locale locale);

    public UserRegistrationResponse findByEntityStatus(int page, int size, String entityStatus, Locale locale);

    public UserRegistrationResponse findAll(int page, int size, Locale locale);

    public UserAuthenticationResponse findByUsernameAndPassword(String username, String password, Locale locale);

    public UserRegistrationResponse changePassword(String username, String oldPassword, String newPassword,
                                          String confirmationPassword, Locale locale);

    public UserAuthenticationResponse resetPassword(String username, Locale locale);

}
