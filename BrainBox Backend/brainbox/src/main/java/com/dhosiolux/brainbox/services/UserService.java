package com.dhosiolux.brainbox.services;

import com.dhosiolux.brainbox.enums.Address;
import com.dhosiolux.brainbox.enums.Gender;
import com.dhosiolux.brainbox.enums.Role;
import com.dhosiolux.brainbox.exceptions.ResourceNotFoundException;
import com.dhosiolux.brainbox.exceptions.UserAlreadyExistsException;
import com.dhosiolux.brainbox.interfaces.UserInterface;
import com.dhosiolux.brainbox.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.dhosiolux.brainbox.enums.Gender.FEMALE;
import static com.dhosiolux.brainbox.enums.Gender.MALE;
import static com.dhosiolux.brainbox.enums.Role.ADMIN;
import static com.dhosiolux.brainbox.enums.Role.ALUMNI;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserService implements UserInterface {

    private List<User> users;

    public void addTestUsers(){
        this.users = new ArrayList<>(
                Arrays.asList(
                        new User("Daniel", "Githiomi", new Random().nextInt((70-22) + 22), MALE, "d.githiomi@alustudent.com", ADMIN, new Address("Grand Baie", 30511L, "Mauritius")),
                        new User("MacDonald", "Nyahoja", new Random().nextInt((70-22) + 22), MALE, "m.nyahoja@alustudent.com", ALUMNI, new Address("Quatre Bornes", 893264L, "Mauritius")),
                        new User("Nabila", "Modan", new Random().nextInt((70-22) + 22), MALE, "n.modan@alustudent.com", ALUMNI, new Address("Maputo", 124567L, "Mozambique"))
                )
        );
    }

    @Override
    public List<User> getAllUsers() {
        return this.users.reversed();
    }

    @Override
    public List<User> getAllAdmins() {
        return this.users.stream().filter(_user -> _user.getUserRole().equals(ADMIN)).collect(Collectors.toList());
    }

    @Override
    public List<User> getAllAlumni() {
        return this.users.stream().filter(_user -> _user.getUserRole().equals(ALUMNI)).collect(Collectors.toList());
    }

    @Override
    public User getUserById(UUID userId){
        return this.users.stream().filter(_user -> _user.getUserId().equals(userId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("No user with ID: " + userId + " was found in the database.", NOT_FOUND));
    }

    @Override
    public User getUserByUsername(String username) {
        return this.users.stream().filter(_user -> _user.getUsername().equals(username)).findFirst().orElseThrow(() -> new ResourceNotFoundException("No user with the username: " + username + " was found in the database.", NOT_FOUND));
    }

    @Override
    public User createNewUser(User user) {

        // Check if user with email already exists
        if(checkIfUserAlreadyExists(user))
            throw new UserAlreadyExistsException("A user with the email " + user.getEmailAddress() + " already exists on the database.");

        User newUser = new User(user.getFirstName(), user.getLastName(), user.getAge(), Enum.valueOf(Gender.class, user.getGender().toString()), user.getEmailAddress(), Enum.valueOf(Role.class, user.getUserRole().toString()), user.getAddress());
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

        User userToUpdate = this.users.stream().filter(_user -> _user.getUserId().equals(user.getUserId())).findFirst().orElseThrow(() -> new ResourceNotFoundException("No user with the ID: " + user.getUserId() + " was found in the database.", NOT_FOUND));

        // Get current user index
        int index = this.users.indexOf(userToUpdate);

        // Remove user from the list
        this.users.remove(userToUpdate);

        this.users.add(index, user);

        return true;
    }

    @Override
    public boolean deleteExistingUser(UUID userId) {
        User userToUpdate = this.users.stream().filter(_user -> _user.getUserId().equals(userId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("No user with the ID: " + userId + " was found in the database.", NOT_FOUND));
        this.users.remove(userToUpdate);
        return true;
    }
}
