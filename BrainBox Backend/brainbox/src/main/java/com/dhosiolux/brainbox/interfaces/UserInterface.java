package com.dhosiolux.brainbox.interfaces;

import com.dhosiolux.brainbox.models.User;

import java.util.Set;
import java.util.UUID;

public interface UserInterface {

    Set<User> getAllUsers();

    Set<User> getAllAdmins();

    Set<User> getAllAlumni();

    User getUserById(UUID userId);

    User getUserByUsername(String username);

    User createNewUser(User user);

    boolean updateExistingUser(User user);

    boolean deleteExistingUser(UUID userId);

}
