package com.app.fitness_app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.fitness_app.entity.User;
import com.app.fitness_app.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public List<User> getAlluser(){
        return userService.getAllUser();
    }

    @PostMapping
    public User saveUser(@Valid @RequestBody User user) {
        
        return userService.saveUser(user);
    }
    
}
