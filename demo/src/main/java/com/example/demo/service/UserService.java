package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    // Constructor injection
    public UserService() {

    }

    // Fetch all users
    public String getAllUsers(String param) {
        return "Get all users";
    }

    // Create a new user
    public String createUser() {
        return "User created";
    }
}