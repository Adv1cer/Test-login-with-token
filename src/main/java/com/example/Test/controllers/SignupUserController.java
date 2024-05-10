package com.example.Test.controllers;

import com.example.Test.dtos.SignupRequest;
import com.example.Test.dtos.UserDTO;
import com.example.Test.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class SignupUserController {

    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?>_createUser(@RequestBody SignupRequest signupRequest){
    UserDTO createdUser = authService.createUser(signupRequest);
    if (createdUser == null)
        return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }
}
