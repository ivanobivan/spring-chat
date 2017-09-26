package ru.messenger.server.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role  {
    ROLE_ANONYMOUS,
    ROLE_USER,
    ROLE_ADMIN;

    public String getRole(){
        return name();
    }
}