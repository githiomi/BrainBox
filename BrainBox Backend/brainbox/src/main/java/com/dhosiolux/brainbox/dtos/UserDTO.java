package com.dhosiolux.brainbox.dtos;

import com.dhosiolux.brainbox.enums.Gender;
import com.dhosiolux.brainbox.enums.Role;
import com.dhosiolux.brainbox.models.Address;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public record UserDTO(
        UUID userId,
        String firstName,
        String lastName,
        String username,
        String emailAddress,
        Integer age,
        Gender gender,
        Role role,
        Address userAddress
) {
}
