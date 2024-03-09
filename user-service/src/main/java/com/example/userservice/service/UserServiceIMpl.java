package com.example.userservice.service;

import com.example.userservice.config.JwtProvider;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceIMpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public User getUserProfile(String jwt) {
        String email = JwtProvider.getEmailFromjwtToken(jwt);

        return userRepo.findByEmail(email);

    }
}
