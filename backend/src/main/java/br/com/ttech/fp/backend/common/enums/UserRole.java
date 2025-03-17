package br.com.ttech.fp.backend.common.enums;

import lombok.Getter;

public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
