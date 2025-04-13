package com.example.usermanagement.service.rest.frontend;

import com.example.usermanagement.service.processor.api.UserRoleProcessor;
import com.example.usermanagement.utils.constants.Constants;
import com.example.usermanagement.utils.requests.CreateUserRoleRequest;
import com.example.usermanagement.utils.responses.UserRoleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@CrossOrigin
@RequestMapping("/user-management/v1/user-role")
public class UserRoleResource {

    private final UserRoleProcessor userRoleProcessor;

    public UserRoleResource(UserRoleProcessor userRoleProcessor) {
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

    @Operation(summary = "Retrieve user groups records as pages")
    @GetMapping(value = "/pages")
    public UserRoleResponse retrieveRoles(@Parameter(name = "Authorization", in = ParameterIn.HEADER,
                                                               description = "Bearer token", required = true)
                                                       String authenticationToken,
                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size,
                                                       @Parameter(description = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                                       @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                               defaultValue = Constants.DEFAULT_LOCALE) final Locale locale)
    {
        return userRoleProcessor.findAll(page, size,locale, authenticationToken);
    }
}
