package com.dhosiolux.brainbox.enums;

import lombok.Getter;

@Getter
public enum Roles {

    MANAGER("Manager"),
    STUDENT("Student");

    private final String role;

    Roles(String role){
        this.role = role;
    }
}
