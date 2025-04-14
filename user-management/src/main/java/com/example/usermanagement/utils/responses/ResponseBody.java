package com.example.usermanagement.utils.responses;

import com.example.usermanagement.utils.constants.Authority;
import com.example.usermanagement.utils.constants.UserAttributes;

import java.util.List;

public class ResponseBody {

    private boolean authenticated;

    private UserAttributes userAttributes;

    private List<Authority> authorities;

    private List<String> userAuthorities;

    private List<String> groups;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public UserAttributes getUserAttributes() {
        return userAttributes;
    }

    public void setUserAttributes(UserAttributes userAttributes) {
        this.userAttributes = userAttributes;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public List<String> getUserAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(List<String> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "authenticated=" + authenticated +
                ", userAttributes=" + userAttributes +
                ", authorities=" + authorities +
                ", userAuthorities=" + userAuthorities +
                ", groups=" + groups +
                '}';
    }
}
