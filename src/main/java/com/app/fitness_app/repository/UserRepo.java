package com.app.fitness_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.fitness_app.entity.User;

@Repository
public interface UserRepo extends JpaRepository <User,Long> {

    Optional<User> findByUserName(String userName);
    
}
