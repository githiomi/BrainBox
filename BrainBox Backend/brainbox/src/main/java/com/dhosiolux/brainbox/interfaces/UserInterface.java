package com.dhosiolux.brainbox.interfaces;

import com.dhosiolux.brainbox.models.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserInterface {

    List<User> getAllUsers();

    List<User> getAllAdmins();

    List<User> getAllAlumni();

    User getUserById(UUID userId);

    User getUserByUsername(String username);

    User createNewUser(User user);

    boolean updateExistingUser(User user);

    boolean deleteExistingUser(UUID userId);

}
