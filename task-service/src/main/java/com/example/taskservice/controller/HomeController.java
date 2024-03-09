package com.example.taskservice.controller;

import com.example.taskservice.dto.UserDto;
import com.example.taskservice.entity.Task;
import com.example.taskservice.entity.TaskStatus;
import com.example.taskservice.service.TaskService;
import com.example.taskservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;


    @GetMapping("/tasks")
    public ResponseEntity<?> getAssignedUserTask() throws Exception{
      return ResponseEntity.status(200).body("Welcom to contidflfm");
    }
}
