package com.sprintgether.otserver.model.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtResponse {

    private String accessToken;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String accessToken, Collection<? extends GrantedAuthority> authorities) {
        this.accessToken = accessToken;
        this.authorities = authorities;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

}
