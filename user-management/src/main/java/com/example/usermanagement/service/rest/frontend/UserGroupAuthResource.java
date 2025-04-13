package com.example.usermanagement.service.rest.frontend;


import com.example.usermanagement.service.processor.api.UserGroupProcessor;
import com.example.usermanagement.utils.constants.Constants;
import com.example.usermanagement.utils.requests.CreateUserGroupRequest;
import com.example.usermanagement.utils.responses.UserGroupResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@CrossOrigin
@RequestMapping("/user-management/v1/user-group")
public class UserGroupAuthResource {

    private final UserGroupProcessor userGroupProcessor;

    public UserGroupAuthResource(UserGroupProcessor userGroupProcessor) {
        this.userGroupProcessor = userGroupProcessor;
    }

    @Operation(summary = "Create a user group")
    @PostMapping(value = "")
    public UserGroupResponse createGroup(@Valid @RequestBody final CreateUserGroupRequest createUserGroupRequest,
                                        @Parameter(name = "Authorization", in = ParameterIn.HEADER,
                                               description = "Bearer token", required = true)
                                       String authenticationToken,
                                        @Parameter(description = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                       @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                               defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return userGroupProcessor.create(createUserGroupRequest,  locale, authenticationToken);
    }

    @Operation(summary = "Assign Roles to User Group")
    @PutMapping(value = "/assign-roles")
    public UserGroupResponse assignRolesToGroup(@RequestParam(value = "userGroupId") long userGroupId,
                                                @RequestParam(value = "userRoleIds") List<Long> userRoleIds,
                                                @Parameter(name = "Authorization", in = ParameterIn.HEADER,
                                                        description = "Bearer token", required = true)
                                                String authenticationToken,
                                                @Parameter(description = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                                @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                        defaultValue = Constants.DEFAULT_LOCALE) final Locale locale){

        return userGroupProcessor.assignUserRolesToGroup(userGroupId, userRoleIds, locale, authenticationToken);

    }

    @Operation(summary = "Retrieve user groups records as pages")
    @GetMapping(value = "/pages")
    public UserGroupResponse retrieveGroups(@Parameter(name = "Authorization", in = ParameterIn.HEADER,
                                                                   description = "Bearer token", required = true)
                                                           String authenticationToken,
                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size,
                                                       @Parameter(description = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                                       @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                               defaultValue = Constants.DEFAULT_LOCALE) final Locale locale)
    {
        return userGroupProcessor.findAll(page, size,locale, authenticationToken);
    }
}
