package com.example.demo.controller;

import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "Operations related to users")
public class UserController {
    private final UserService userService;
    @Autowired
    private RestTemplate restTemplate;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    @Operation(summary = "Get all users", description = "Returns a list of all registered users")
    public String getUsers() {
        return userService.getAllUsers("Red dates");
    }

    @GetMapping("/callMessage")
    public String callMessage() {
        String url = "http://localhost:8081/api/messages/getMessage"; // Update with actual host/port
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/callMessage2")
    public String callMessage2() {
        String url = "http://localhost:8081/api/messages/getMessage2"; // Update with actual host/port
        return restTemplate.getForObject(url, String.class);
    }

    @PostMapping("/createUser")
    @Operation(summary = "Create a new user", description = "Adds a new user to the system")
    public String createUser(@RequestBody Object obj) {
        return userService.createUser();
    }
}
