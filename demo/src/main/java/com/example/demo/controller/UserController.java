package com.example.demo.controller;

import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "Operations related to users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    @Operation(summary = "Get all users", description = "Returns a list of all registered users")
    public String getUsers() {
        return userService.getAllUsers("Red dates");
    }

    @PostMapping("/createUser")
    @Operation(summary = "Create a new user", description = "Adds a new user to the system")
    public String createUser(@RequestBody Object obj) {
        return userService.createUser();
    }
}