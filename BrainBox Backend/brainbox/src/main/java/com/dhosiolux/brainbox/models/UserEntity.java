package com.dhosiolux.brainbox.models;

import com.dhosiolux.brainbox.enums.Gender;
import com.dhosiolux.brainbox.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserEntity {

    private final UUID userId = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int age;
    private Gender gender;
    private String emailAddress;
    private Role userRole;
    private Address address;

    public UserEntity(String firstName, String lastName, String password, int age, Gender gender, String emailAddress, Role role, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.emailAddress = emailAddress;
        this.userRole = role;
        this.address = address;
        this.username = generateUsername(firstName, lastName);
        this.password = password;
    }

    private String generateUsername(String firstName, String lastName) {
        String lastNameSubString;

        if (lastName.length() < 4)
            lastNameSubString = lastName.substring(0, lastName.length());
        else
            lastNameSubString = lastName.substring(0, 4);

        return (firstName.charAt(0) + lastNameSubString + generateNumberSuffix()).toUpperCase();
    }

    private String generateNumberSuffix() {
        int min = 100, max = 999;
        int randomInt = new Random().nextInt((max - min) + 1) + min;
        return String.valueOf(randomInt);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        UserEntity that = (UserEntity) o;
        return getUserId() != null && Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
