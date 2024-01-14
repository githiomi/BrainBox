package com.dhosiolux.brainbox.models;

import com.dhosiolux.brainbox.enums.Address;
import com.dhosiolux.brainbox.enums.Gender;
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
    private int age;
    private Gender gender;
    private String emailAddress;
    private Role userRole;
    private Address address;

    public User(String firstName, String lastName, int age, Gender gender, String emailAddress, Role role, Address address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.emailAddress = emailAddress;
        this.userRole = role;
        this.address = address;
        this.username = generateUsername(firstName, lastName);
    }

    private String generateUsername(String firstName, String lastName){
        String lastNameSubString;

        if(lastName.length() < 4)
            lastNameSubString = lastName.substring(0, lastName.length());
        else
            lastNameSubString = lastName.substring(0,4);

        return (firstName.charAt(0) + lastNameSubString + generateNumberSuffix()).toUpperCase();
    }

    private String generateNumberSuffix(){
        int min = 100, max = 999;
        int randomInt = new Random().nextInt((max - min) + 1) + min;
        return String.valueOf(randomInt);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return userId == ((User) o).getUserId();
    }

}
