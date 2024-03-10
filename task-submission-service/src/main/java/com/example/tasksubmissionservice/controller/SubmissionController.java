package com.example.tasksubmissionservice.controller;

import com.example.tasksubmissionservice.dto.UserDto;
import com.example.tasksubmissionservice.entity.Submission;
import com.example.tasksubmissionservice.service.SubmissionService;
import com.example.tasksubmissionservice.service.TaskService;
import com.example.tasksubmissionservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
@Slf4j
public class SubmissionController {
    @Autowired
    private SubmissionService submissionService;

    @Autowired
    UserService userService;

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<?> submitTask(
            @RequestParam long taskId, @RequestParam String githubLink,
            @RequestHeader("Authorization") String jwt) throws Exception{
        log.info("in submit task method");
        UserDto user = userService.getUserProfile(jwt);
        if(user == null) throw new Exception("User Not Jwt is not authenticated");
        Submission submission = submissionService.sumbitTask(taskId, githubLink, user.getId(), jwt);
        return ResponseEntity.status(201).body(submission);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubmissionById(
            @PathVariable long id,
            @RequestHeader("Authorization") String jwt) throws Exception{
//        UserDto user = userService.getUserProfile(jwt);
        Submission submission = submissionService.getTaskSubmissionByID(id);
        return ResponseEntity.status(201).body(submission);
    }

    @GetMapping
    public ResponseEntity<?> getAllSubmissions(
            @RequestHeader("Authorization") String jwt) throws Exception{

        List<Submission> submissions = submissionService.getAllTaskSubmissions();
        return ResponseEntity.status(201).body(submissions);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<?> getAllSubmissions(
            @PathVariable long taskId,
            @RequestHeader("Authorization") String jwt) throws Exception{
//        UserDto user = userService.getUserProfile(jwt);
        List<Submission> submissions = submissionService.getTaskSubmissionByTaskId(taskId);
        return ResponseEntity.status(201).body(submissions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> acceptOrDeclineSubmissions(
            @PathVariable long id,
            @RequestParam("status") String status,
            @RequestHeader("Authorization") String jwt) throws Exception{
//        UserDto user = userService.getUserProfile(jwt);
        Submission submission = submissionService.acceptDeclineSubmission(id,status);
        return ResponseEntity.status(201).body(submission);
    }

}
