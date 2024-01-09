package com.dhosiolux.brainbox.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.UUID;

@Data
public class User {
    final UUID userId = UUID.randomUUID();
    String firstName;
    String lastName;
    String username;
    Role userRole;

    public User(String firstName, String lastName, Role role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = (firstName.charAt(0) + lastName.substring(0,4)).toUpperCase() + generateNumberSuffix();
    }

    private String generateNumberSuffix(){
        int min = 100, max = 999;
        int randomInt = new Random().nextInt((max - min) + 1) + min;
        return String.valueOf(randomInt);
    }

}
