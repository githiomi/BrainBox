package com.dhosiolux.brainbox.interfaces;

import com.dhosiolux.brainbox.models.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserInterface {

    List<UserEntity> getAllUsers();

    List<UserEntity> getAllAdmins();

    List<UserEntity> getAllAlumni();

    UserEntity getUserById(UUID userId);

    UserEntity getUserByUsername(String username);

    UserEntity createNewUser(UserEntity userEntity);

    boolean updateExistingUser(UserEntity userEntity);

    boolean deleteExistingUser(UUID userId);

}
