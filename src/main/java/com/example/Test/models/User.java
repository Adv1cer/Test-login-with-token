package com.example.Test.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "login")
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String password;
}
