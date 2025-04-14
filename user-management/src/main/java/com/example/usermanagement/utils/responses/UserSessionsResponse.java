package com.example.usermanagement.utils.responses;

import com.example.usermanagement.utils.dto.UserSessionsDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Page;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSessionsResponse extends CommonResponse{

    private UserSessionsDto userSessionsDto;
    private List<UserSessionsDto> userSessionsDtoList;
    private Page<UserSessionsDto> userSessionsDtoPage;

    public UserSessionsDto getUserSessionsDto() {
        return userSessionsDto;
    }

    public void setUserSessionsDto(UserSessionsDto userSessionsDto) {
        this.userSessionsDto = userSessionsDto;
    }

    public List<UserSessionsDto> getUserSessionsDtoList() {
        return userSessionsDtoList;
    }

    public void setUserSessionsDtoList(List<UserSessionsDto> userSessionsDtoList) {
        this.userSessionsDtoList = userSessionsDtoList;
    }

    public Page<UserSessionsDto> getUserSessionsDtoPage() {
        return userSessionsDtoPage;
    }

    public void setUserSessionsDtoPage(Page<UserSessionsDto> userSessionsDtoPage) {
        this.userSessionsDtoPage = userSessionsDtoPage;
    }

    @Override
    public String toString() {
        return "UserSessionsResponse{" +
                "userSessionsDto=" + userSessionsDto +
                ", userSessionsDtoList=" + userSessionsDtoList +
                ", userSessionsDtoPage=" + userSessionsDtoPage +
                '}';
    }
}
