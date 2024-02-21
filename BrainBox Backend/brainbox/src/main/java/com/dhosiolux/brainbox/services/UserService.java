package com.dhosiolux.brainbox.services;

import com.dhosiolux.brainbox.models.Address;
import com.dhosiolux.brainbox.enums.Gender;
import com.dhosiolux.brainbox.enums.Role;
import com.dhosiolux.brainbox.exceptions.ResourceNotFoundException;
import com.dhosiolux.brainbox.exceptions.UserAlreadyExistsException;
import com.dhosiolux.brainbox.interfaces.UserInterface;
import com.dhosiolux.brainbox.models.UserEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.dhosiolux.brainbox.enums.Gender.MALE;
import static com.dhosiolux.brainbox.enums.Role.ADMIN;
import static com.dhosiolux.brainbox.enums.Role.ALUMNI;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserService implements UserInterface {

    private List<UserEntity> userEntities;

    public void addTestUsers(){
        this.userEntities = new ArrayList<>(
                Arrays.asList(
                        new UserEntity("Daniel", "Githiomi", UUID.randomUUID().toString(), new Random().nextInt((70-22) + 22), MALE, "d.githiomi@alustudent.com", ADMIN, new Address("Grand Baie", 30511L, "Mauritius")),
                        new UserEntity("MacDonald", "Nyahoja", UUID.randomUUID().toString(), new Random().nextInt((70-22) + 22), MALE, "m.nyahoja@alustudent.com", ALUMNI, new Address("Quatre Bornes", 893264L, "Mauritius")),
                        new UserEntity("Nabila", "Modan", UUID.randomUUID().toString(), new Random().nextInt((70-22) + 22), MALE, "n.modan@alustudent.com", ALUMNI, new Address("Maputo", 124567L, "Mozambique"))
                )
        );
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return this.userEntities.reversed();
    }

    @Override
    public List<UserEntity> getAllAdmins() {
        return this.userEntities.stream().filter(_user -> _user.getUserRole().equals(ADMIN)).collect(Collectors.toList());
    }

    @Override
    public List<UserEntity> getAllAlumni() {
        return this.userEntities.stream().filter(_user -> _user.getUserRole().equals(ALUMNI)).collect(Collectors.toList());
    }

    @Override
    public UserEntity getUserById(UUID userId){
        return this.userEntities.stream().filter(_user -> _user.getUserId().equals(userId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("No user with ID: " + userId + " was found in the database.", NOT_FOUND));
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return this.userEntities.stream().filter(_user -> _user.getUsername().equals(username)).findFirst().orElseThrow(() -> new ResourceNotFoundException("No user with the username: " + username + " was found in the database.", NOT_FOUND));
    }

    @Override
    public UserEntity createNewUser(UserEntity userEntity) {

        // Check if user with email already exists
        if(checkIfUserAlreadyExists(userEntity))
            throw new UserAlreadyExistsException("A user with the email " + userEntity.getEmailAddress() + " already exists on the database.");

        UserEntity newUserEntity = new UserEntity(userEntity.getFirstName(), userEntity.getLastName(), UUID.randomUUID().toString(), userEntity.getAge(), Enum.valueOf(Gender.class, userEntity.getGender().toString()), userEntity.getEmailAddress(), Enum.valueOf(Role.class, userEntity.getUserRole().toString()), userEntity.getAddress());
        this.userEntities.add(newUserEntity);

        return newUserEntity;
    }

    private boolean checkIfUserAlreadyExists(UserEntity userEntity){
        return this.userEntities.stream().anyMatch(_user -> _user.getEmailAddress().equals(userEntity.getEmailAddress()));
    }

    @Override
    public boolean updateExistingUser(UserEntity userEntity){
        if (this.getUserById(userEntity.getUserId()) == null)
            return false;

        UserEntity userEntityToUpdate = this.userEntities.stream().filter(_user -> _user.getUserId().equals(userEntity.getUserId())).findFirst().orElseThrow(() -> new ResourceNotFoundException("No user with the ID: " + userEntity.getUserId() + " was found in the database.", NOT_FOUND));

        // Get current user index
        int index = this.userEntities.indexOf(userEntityToUpdate);

        // Remove user from the list
        this.userEntities.remove(userEntityToUpdate);

        this.userEntities.add(index, userEntity);

        return true;
    }

    @Override
    public boolean deleteExistingUser(UUID userId) {
        UserEntity userEntityToUpdate = this.userEntities.stream().filter(_user -> _user.getUserId().equals(userId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("No user with the ID: " + userId + " was found in the database.", NOT_FOUND));
        this.userEntities.remove(userEntityToUpdate);
        return true;
    }
}
