package com.app.fitness_app.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.fitness_app.entity.User;

import jakarta.validation.Valid;


public interface UserService extends UserDetailsService{

    public List<User> getAllUser();

    public User saveUser(@Valid User user);

    public UserDetails loadUserByUserName(String userName);
    
}
