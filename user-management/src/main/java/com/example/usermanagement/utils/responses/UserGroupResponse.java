package com.example.usermanagement.utils.responses;

import com.example.usermanagement.utils.dto.UserGroupDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Page;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGroupResponse extends CommonResponse {

    private UserGroupDto userGroupDto;
    private List<UserGroupDto> userGroupDtoList;
    private Page<UserGroupDto> userGroupDtoPage;

    public UserGroupDto getUserGroupDto() {
        return userGroupDto;
    }

    public void setUserGroupDto(UserGroupDto userGroupDto) {
        this.userGroupDto = userGroupDto;
    }

    public List<UserGroupDto> getUserGroupDtoList() {
        return userGroupDtoList;
    }

    public void setUserGroupDtoList(List<UserGroupDto> userGroupDtoList) {
        this.userGroupDtoList = userGroupDtoList;
    }

    public Page<UserGroupDto> getUserGroupDtoPage() {
        return userGroupDtoPage;
    }

    public void setUserGroupDtoPage(Page<UserGroupDto> userGroupDtoPage) {
        this.userGroupDtoPage = userGroupDtoPage;
    }

    @Override
    public String toString() {
        return "UserGroupResponse{" +
                "userGroupDto=" + userGroupDto +
                ", userGroupDtoList=" + userGroupDtoList +
                ", userGroupDtoPage=" + userGroupDtoPage +
                '}';
    }
}
