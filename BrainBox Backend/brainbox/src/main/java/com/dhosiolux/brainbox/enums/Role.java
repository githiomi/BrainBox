package com.dhosiolux.brainbox.enums;

import lombok.Data;

public enum Role {

    ADMIN("Administrator"),
    ALUMNI("Alumni");

    private final String role;

    Role(String role){
        this.role = role;
    }

}
