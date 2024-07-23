package com.app.fitness_app.service;

import java.util.List;

import com.app.fitness_app.entity.User;

import jakarta.validation.Valid;


public interface UserService {

    public List<User> getAllUser();

    public User saveUser(@Valid User user);
    
}
