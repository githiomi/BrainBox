package com.dhosiolux.brainbox.models;

import com.dhosiolux.brainbox.enums.Role;
import lombok.Data;

import java.util.Random;
import java.util.UUID;

@Data
public class User {
    private final UUID userId = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private String username;
    private Role userRole;

    public User(String firstName, String lastName, Role role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = (firstName.charAt(0) + lastName.substring(0,4)).toUpperCase() + generateNumberSuffix();
        this.userRole = role;
    }

    private String generateNumberSuffix(){
        int min = 100, max = 999;
        int randomInt = new Random().nextInt((max - min) + 1) + min;
        return String.valueOf(randomInt);
    }

}
