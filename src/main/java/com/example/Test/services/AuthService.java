package com.example.Test.services;

import com.example.Test.dtos.SignupRequest;
import com.example.Test.dtos.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupRequest signupRequest);
}
