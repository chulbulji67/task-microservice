package com.example.taskservice.controller;

import com.example.taskservice.dto.UserDto;
import com.example.taskservice.entity.Task;
import com.example.taskservice.entity.TaskStatus;
import com.example.taskservice.service.TaskService;
import com.example.taskservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestHeader("Authorization")String jwt) throws Exception {
        UserDto userProfile = userService.getUserProfile(jwt);
        Task createdTask = taskService.createTask(task, userProfile.getRole());
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable long id ,@RequestHeader("Authorization")String jwt) throws Exception{
        UserDto userDto = userService.getUserProfile(jwt);

        Task task = taskService.getTaskById(id);
        return ResponseEntity.status(200).body(task);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAssignedUserTask(@RequestParam(required = false)TaskStatus status, @RequestHeader("Authorization")String jwt) throws Exception{
        UserDto userDto = userService.getUserProfile(jwt);

        List<Task> tasks = taskService.assignedUserTask(userDto.getId(), status);
        return ResponseEntity.status(200).body(tasks);
    }

    @GetMapping
    public ResponseEntity<?> getAllTasks(@RequestParam(required = false)TaskStatus status, @RequestHeader("Authorization")String jwt) throws Exception{
        UserDto userDto = userService.getUserProfile(jwt);

        List<Task> tasks = taskService.getAllTasks(status);
        return ResponseEntity.status(200).body(tasks);
    }

    @PutMapping("/{id}/user/{userId}/assigned")
    public ResponseEntity<?> assignedTaskToUser(@PathVariable long id, @PathVariable long userId, @RequestHeader("Authorization")String jwt) throws Exception{
        UserDto userDto = userService.getUserProfile(jwt);

        Task task = taskService.assignedToUser(userId, id);
        return ResponseEntity.status(200).body(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable long id,@RequestBody Task req, @RequestHeader("Authorization")String jwt) throws Exception{
        UserDto userDto = userService.getUserProfile(jwt);

        Task task = taskService.updateTask(id, req, userDto.getId());
        return ResponseEntity.status(200).body(task);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<?> completeTask(@PathVariable long id) throws Exception{

        Task task = taskService.completeTask(id);
        return ResponseEntity.status(200).body(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable long id ,@RequestHeader("Authorization")String jwt) throws Exception{
        UserDto userDto = userService.getUserProfile(jwt);

        taskService.deleteTask(id);
        return ResponseEntity.status(200).body("Task Deleted Successfully");

}
}
