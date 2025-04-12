package com.example.usermanagement.utils.responses;

import com.example.usermanagement.utils.dto.UserRoleDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Page;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleResponse extends CommonResponse {

    private  UserRoleDto userRoleDto;
    private List<UserRoleDto> userRoleDtoList;
    private Page<UserRoleDto> userRolesPage;

    public UserRoleDto getUserRoleDto() {
        return userRoleDto;
    }

    public void setUserRoleDto(UserRoleDto userRoleDto) {
        this.userRoleDto = userRoleDto;
    }

    public List<UserRoleDto> getUserRoleDtoList() {
        return userRoleDtoList;
    }

    public void setUserRoleDtoList(List<UserRoleDto> userRoleDtoList) {
        this.userRoleDtoList = userRoleDtoList;
    }

    public Page<UserRoleDto> getUserRolesPage() {
        return userRolesPage;
    }

    public void setUserRolesPage(Page<UserRoleDto> userRolesPage) {
        this.userRolesPage = userRolesPage;
    }

    @Override
    public String toString() {
        return "UserRoleResponse{" +
                "userRoleDto=" + userRoleDto +
                ", userRoleDtoList=" + userRoleDtoList +
                ", userRolesPage=" + userRolesPage +
                '}';
    }
}
