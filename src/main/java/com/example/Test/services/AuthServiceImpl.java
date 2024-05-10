package com.example.Test.services;

import com.example.Test.dtos.SignupRequest;
import com.example.Test.dtos.UserDTO;
import com.example.Test.models.User;
import com.example.Test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPhone(signupRequest.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode((signupRequest.getPassword())));
        User createUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(createUser.getEmail());
        userDTO.setName(createUser.getName());
        userDTO.setPhone(createUser.getPhone());
        return userDTO;
    }
}
