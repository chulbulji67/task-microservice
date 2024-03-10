package com.example.tasksubmissionservice.service;

import com.example.tasksubmissionservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="USER-SERVICE", url = "http://localhost:9091")
public interface UserService {
    @GetMapping("/api/users/profile")
    public UserDto getUserProfile(@RequestHeader("Authorization") String jwt);
}