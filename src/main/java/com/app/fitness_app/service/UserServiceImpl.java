package com.app.fitness_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.fitness_app.entity.User;
import com.app.fitness_app.repository.UserRepo;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
   
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    
    public User saveUser(@Valid User user) {
       return userRepo.save(user);
    }
    
}
