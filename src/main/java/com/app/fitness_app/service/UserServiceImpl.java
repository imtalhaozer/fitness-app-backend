package com.app.fitness_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.fitness_app.entity.User;
import com.app.fitness_app.repository.UserRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepo userRepo,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }
   
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    
    public User saveUser(@Valid User user) {
        User neWuser=User.builder()
                        .userPassword(bCryptPasswordEncoder.encode(user.getPassword()))
                        .accountNonExpired(true)
                        .credentialsNonExpired(true)
                        .isEnabled(true)
                        .accountNonLocked(true)
                        .userMail(user.getUserMail())
                        .userName(user.getUsername())
                        .userSurname(user.getUserSurname())
                        .userPhone(user.getUserPhone())
                        .userProgram(user.getUserProgram())
                        .build();

       return userRepo.save(neWuser);
    }

    @Override
    public UserDetails loadUserByUserName(String userName) throws UsernameNotFoundException {
        Optional<User> user=userRepo.findByUserName(userName);
        return user.orElseThrow(()->new EntityNotFoundException("entity not found"+userName));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }
    
}
