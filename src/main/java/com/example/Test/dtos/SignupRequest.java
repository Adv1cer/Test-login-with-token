package com.example.Test.dtos;

import lombok.Data;
@Data
public class SignupRequest {

    private String name;

    private String email;

    private String phone;

    private String password;
}
