package com.dhosiolux.brainbox.controllers;

import com.dhosiolux.brainbox.models.ResourceResponse;
import com.dhosiolux.brainbox.models.User;
import com.dhosiolux.brainbox.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v1.0/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    private ResponseEntity<ResourceResponse<Set<User>>> getAllUsers() {
        return ResponseEntity.status(200).body(new ResourceResponse<Set<User>>(this.userService.getAllUsers().size() + " users were found.", this.userService.getAllUsers(), LocalDateTime.now()));
    }

    @GetMapping("/admins")
    private ResponseEntity<ResourceResponse<Set<User>>> getAdminUsers() {
        return ResponseEntity.status(200).body(new ResourceResponse<Set<User>>(this.userService.getAllAdmins().size() + " admin users were found.", this.userService.getAllAdmins(), LocalDateTime.now()));
    }

    @GetMapping("/alumni")
    private ResponseEntity<ResourceResponse<Set<User>>> getAlumniUsers() {
        return ResponseEntity.status(200).body(new ResourceResponse<Set<User>>(this.userService.getAllAlumni().size() + " alumni users were found.", this.userService.getAllAlumni(), LocalDateTime.now()));
    }

    @PostMapping("")
    private ResponseEntity<ResourceResponse<User>> createNewUser(@RequestBody User user) {
        return ResponseEntity.status(CREATED).body(new ResourceResponse<User>("A new user has been created.", this.userService.createNewUser(user), LocalDateTime.now()));
    }
}
