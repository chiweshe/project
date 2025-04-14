package com.example.usermanagement.service.rest.frontend;

import com.example.usermanagement.service.processor.api.UserRegistrationProcessor;
import com.example.usermanagement.utils.constants.Constants;
import com.example.usermanagement.utils.requests.CreateUserRequest;
import com.example.usermanagement.utils.responses.UserRegistrationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;

@RestController
@CrossOrigin
@RequestMapping("/user-management/v1/user-registration")
public class UserLoginAuthResource {

    private final UserRegistrationProcessor userRegistrationProcessor;

    public UserLoginAuthResource(UserRegistrationProcessor userRegistrationProcessor) {
        this.userRegistrationProcessor = userRegistrationProcessor;
    }

    @Operation(summary = "Create a user account")
    @PostMapping(value = "")
    public UserRegistrationResponse createUser(@Valid @RequestBody final CreateUserRequest createUserGroupRequest,
                                                @Parameter(name = "Authorization", in = ParameterIn.HEADER,
                                                 description = "Bearer token", required = true)
                                         String authenticationToken,
                                                @Parameter(description = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                         @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                 defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return userRegistrationProcessor.registerUser(createUserGroupRequest,  locale, authenticationToken);
    }
}
