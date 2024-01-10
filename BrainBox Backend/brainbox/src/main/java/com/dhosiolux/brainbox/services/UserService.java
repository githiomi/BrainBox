package com.dhosiolux.brainbox.services;

import com.dhosiolux.brainbox.enums.Role;
import com.dhosiolux.brainbox.exceptions.ResourceNotFoundException;
import com.dhosiolux.brainbox.interfaces.UserInterface;
import com.dhosiolux.brainbox.models.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.dhosiolux.brainbox.enums.Role.ADMIN;
import static com.dhosiolux.brainbox.enums.Role.ALUMNI;

@Service
public class UserService implements UserInterface {

    private Set<User> users = new HashSet<>(
            Arrays.asList(
                    new User("Daniel", "Githiomi", ADMIN),
                    new User("MacDonald", "Nyahoja", ALUMNI),
                    new User("Nabila", "Modan", ALUMNI)
            )
    );

    @Override
    public Set<User> getAllUsers() {
        return this.users;
    }

    @Override
    public Set<User> getAllAdmins() {
        return this.users.stream().filter(_user -> _user.getUserRole().equals(ADMIN)).collect(Collectors.toSet());
    }

    @Override
    public Set<User> getAllAlumni() {
        return this.users.stream().filter(_user -> _user.getUserRole().equals(ALUMNI)).collect(Collectors.toSet());
    }

    @Override
    public User createNewUser(User user) {

        // Check if user with email already exists
        if(checkIfUserAlreadyExists(user))
            throw new UserAlreadyExistsException(user);

        User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmailAddress(), user.getUserRole());
        this.users.add(newUser);
        return newUser;
    }

    private boolean checkIfUserAlreadyExists(User user){
        return this.users.stream().anyMatch(_user -> _user.getEmailAddress().equals(user.getEmailAddress()));
    }
}
