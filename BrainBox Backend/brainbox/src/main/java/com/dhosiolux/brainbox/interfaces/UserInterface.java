package com.dhosiolux.brainbox.interfaces;

import com.dhosiolux.brainbox.models.User;

import java.util.Set;

public interface UserInterface {

    Set<User> getAllUsers();

    Set<User> getAllAdmins();

    Set<User> getAllAlumni();

}
