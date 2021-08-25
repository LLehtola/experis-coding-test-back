package com.experis.experiscodingtestback.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class Admin {
    private String id;
    private Collection<? extends GrantedAuthority> authorities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
