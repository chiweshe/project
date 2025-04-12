package com.example.usermanagement.service.rest.frontend;

import com.example.usermanagement.service.processor.api.UserRoleProcessor;
import com.example.usermanagement.utils.constants.Constants;
import com.example.usermanagement.utils.requests.CreateUserRoleRequest;
import com.example.usermanagement.utils.responses.UserRoleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@CrossOrigin
@RequestMapping("/user-management/v1/user-role")
public class UserLoginAuthResource {

    private final UserRoleProcessor userRoleProcessor;

    public UserLoginAuthResource(UserRoleProcessor userRoleProcessor) {
        this.userRoleProcessor = userRoleProcessor;
    }

    @Operation(summary = "Create a user role")
    @PostMapping(value = "")
    public UserRoleResponse createRole(@Valid @RequestBody final CreateUserRoleRequest createUserRoleRequest,
                                        @Parameter(name = "Authorization", in = ParameterIn.HEADER,
                                             description = "Bearer token", required = true)
                                     String authenticationToken,
                                        @Parameter(description = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                     @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                             defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return userRoleProcessor.create(createUserRoleRequest,  locale, authenticationToken);
    }
}
