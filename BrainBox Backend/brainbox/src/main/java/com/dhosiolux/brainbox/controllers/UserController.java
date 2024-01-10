package com.dhosiolux.brainbox.controllers;

import com.dhosiolux.brainbox.models.ResourceResponse;
import com.dhosiolux.brainbox.models.User;
import com.dhosiolux.brainbox.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("api/v1.0/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    private ResponseEntity<ResourceResponse<Set<User>>> getAllUsers() {
        return ResponseEntity.status(200).body(new ResourceResponse<Set<User>>(this.userService.getAllUsers().size() + "admin users were found", this.userService.getAllUsers(), LocalDateTime.now()));
    }
}
