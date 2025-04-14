package com.example.usermanagement.utils.responses;

import com.example.usermanagement.utils.dto.UserRegistrationDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Page;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegistrationResponse extends CommonResponse {

    private UserRegistrationDto userRegistrationDto;
    private List<UserRegistrationDto> userRegistrationDtoList;
    private Page<UserRegistrationDto> userRegistrationDtoPage;

    public UserRegistrationDto getUserRegistrationDto() {
        return userRegistrationDto;
    }

    public void setUserRegistrationDto(UserRegistrationDto userRegistrationDto) {
        this.userRegistrationDto = userRegistrationDto;
    }

    public List<UserRegistrationDto> getUserRegistrationDtoList() {
        return userRegistrationDtoList;
    }

    public void setUserRegistrationDtoList(List<UserRegistrationDto> userRegistrationDtoList) {
        this.userRegistrationDtoList = userRegistrationDtoList;
    }

    public Page<UserRegistrationDto> getUserRegistrationDtoPage() {
        return userRegistrationDtoPage;
    }

    public void setUserRegistrationDtoPage(Page<UserRegistrationDto> userRegistrationDtoPage) {
        this.userRegistrationDtoPage = userRegistrationDtoPage;
    }

    @Override
    public String toString() {
        return "UserRegistrationResponse{" +
                "userRegistrationDto=" + userRegistrationDto +
                ", userRegistrationDtoList=" + userRegistrationDtoList +
                ", userRegistrationDtoPage=" + userRegistrationDtoPage +
                '}';
    }
}
