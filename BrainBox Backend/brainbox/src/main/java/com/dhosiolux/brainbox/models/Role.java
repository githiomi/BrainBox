package com.dhosiolux.brainbox.models;

import lombok.Data;

public enum Role {

    ADMIN("Administrator"),
    ALUMNI("Alumni");

    private final String role;

    Role(String role){
        this.role = role;
    }

}
