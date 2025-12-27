package com.example.socialgraphengine.model.enums;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN;

    public String getAuthority() {
       return this.toString();
    }
}
