package com.example.Test.controllers;

import com.example.Test.dtos.AuthenticationRequest;
import com.example.Test.dtos.AuthenticationResponse;
import com.example.Test.services.jwt.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import com.example.Test.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;


import java.io.IOException;

@RestController
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @PostMapping("/authentication")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
    try{
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
    }catch(BadCredentialsException e){
        throw new BadCredentialsException("Incorrect Username or password");
    }catch (DisabledException disabledException){
        response.sendError(HttpServletResponse.SC_NOT_FOUND,"User is not in the system, Register is required.");
        return null;
    }
    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

    final String jwt = jwtUtil.generateToken(userDetails.getUsername());

    return new AuthenticationResponse(jwt);

    }
}
