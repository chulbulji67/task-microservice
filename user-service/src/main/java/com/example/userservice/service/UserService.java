package com.example.userservice.service;

import com.example.userservice.entity.User;

public interface UserService {

    public User getUserProfile(String jwt);
}
