package com.dhosiolux.brainbox.controllers;

import com.dhosiolux.brainbox.models.ResourceResponse;
import com.dhosiolux.brainbox.models.UserEntity;
import com.dhosiolux.brainbox.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1.0/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    private ResponseEntity<ResourceResponse<List<UserEntity>>> getAllUsers() {
        return ResponseEntity.status(200).body(new ResourceResponse<List<UserEntity>>(this.userService.getAllUsers().size() + " users were found.", this.userService.getAllUsers(), LocalDateTime.now()));
    }

    @GetMapping("/admins")
    private ResponseEntity<ResourceResponse<List<UserEntity>>> getAdminUsers() {
        return ResponseEntity.status(200).body(new ResourceResponse<List<UserEntity>>(this.userService.getAllAdmins().size() + " admin users were found.", this.userService.getAllAdmins(), LocalDateTime.now()));
    }

    @GetMapping("/alumni")
    private ResponseEntity<ResourceResponse<List<UserEntity>>> getAlumniUsers() {
        return ResponseEntity.status(200).body(new ResourceResponse<List<UserEntity>>(this.userService.getAllAlumni().size() + " alumni users were found.", this.userService.getAllAlumni(), LocalDateTime.now()));
    }

    @GetMapping("/{id}")
    private ResponseEntity<ResourceResponse<UserEntity>> getUserById(@PathVariable("id") UUID userId){
        return ResponseEntity.status(200).body(new ResourceResponse<UserEntity>("User with ID: " + userId + " was found on the database", this.userService.getUserById(userId), LocalDateTime.now()));
    }

    @PostMapping("")
    private ResponseEntity<ResourceResponse<UserEntity>> createNewUser(@RequestBody UserEntity userEntity) {
        return ResponseEntity.status(CREATED).body(new ResourceResponse<UserEntity>("A new user has been created.", this.userService.createNewUser(userEntity), LocalDateTime.now()));
    }

    @PutMapping("/update")
    private ResponseEntity<ResourceResponse<Boolean>> updateExistingUser(@RequestBody UserEntity userEntity){
        return ResponseEntity.status(OK).body(new ResourceResponse<Boolean>("The user with id: " + userEntity.getUserId() + " has been updated successfully", this.userService.updateExistingUser(userEntity), LocalDateTime.now()));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<ResourceResponse<Boolean>> deleteExistingUser(@PathVariable("id") UUID userId){
        return ResponseEntity.status(OK).body(new ResourceResponse<Boolean>("The user with id: " + userId + " has been deleted successfully", this.userService.deleteExistingUser(userId), LocalDateTime.now()));
    }
}
