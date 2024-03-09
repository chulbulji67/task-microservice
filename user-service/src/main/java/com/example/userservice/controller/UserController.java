package com.example.userservice.controller;

import com.example.userservice.entity.User;
import com.example.userservice.service.UserServiceIMpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceIMpl userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader ("Authorization") String jwt){
       User user = userService.getUserProfile(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
