package com.dhosiolux.brainbox.services;

import com.dhosiolux.brainbox.enums.Role;
import com.dhosiolux.brainbox.exceptions.ResourceNotFoundException;
import com.dhosiolux.brainbox.exceptions.UserAlreadyExistsException;
import com.dhosiolux.brainbox.interfaces.UserInterface;
import com.dhosiolux.brainbox.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.dhosiolux.brainbox.enums.Role.ADMIN;
import static com.dhosiolux.brainbox.enums.Role.ALUMNI;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserService implements UserInterface {

    private Set<User> users;

    public void addTestUsers(){
        this.users = new HashSet<>(
                Arrays.asList(
                        new User("Daniel", "Githiomi", "d.githiomi@alustudent.com", ADMIN),
                        new User("MacDonald", "Nyahoja", "m.nyahoja@alustudent.com", ALUMNI),
                        new User("Nabila", "Modan", "n.modan@alustudent.com", ALUMNI)
                )
        );
    }

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

    public User getUserById(UUID userId){
        return this.users.stream().filter(_user -> _user.getUserId().equals(userId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("No user with id " + userId + " was found in the database.", NOT_FOUND));
    }

    @Override
    public User createNewUser(User user) {

        // Check if user with email already exists
        if(checkIfUserAlreadyExists(user))
            throw new UserAlreadyExistsException("A user with the email " + user.getEmailAddress() + " already exists on the database.");

        User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmailAddress(), Enum.valueOf(Role.class, user.getUserRole().toString()));
        this.users.add(newUser);

        return newUser;
    }

    private boolean checkIfUserAlreadyExists(User user){
        return this.users.stream().anyMatch(_user -> _user.getEmailAddress().equals(user.getEmailAddress()));
    }

    @Override
    public boolean updateExistingUser(User user){
        if (this.getUserById(user.getUserId()) == null)
            return false;

        User userToUpdate = this.users.stream().filter(_user -> _user.getUserId().equals(user.getUserId())).findFirst().orElseThrow(() -> new ResourceNotFoundException("No user with id " + user.getUserId() + " was found in the database.", NOT_FOUND));
        this.users.remove(userToUpdate);
        this.users.add(user);

        return true;
    }

    @Override
    public boolean deleteExistingUser(UUID userId) {
        User userToUpdate = this.users.stream().filter(_user -> _user.getUserId().equals(userId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("No user with id " + userId + " was found in the database.", NOT_FOUND));
        this.users.remove(userToUpdate);
        return true;
    }
}
