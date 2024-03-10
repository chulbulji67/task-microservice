package com.example.tasksubmissionservice.controller;

import com.example.tasksubmissionservice.entity.Submission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class Check {
    @GetMapping("/check")
    public ResponseEntity<?> getAllSubmissions(
            @RequestHeader("Authorization") String jwt) throws Exception{
log.info("Working Check");
//        List<Submission> submissions = submissionService.getAllTaskSubmissions();
        return ResponseEntity.status(201).body("Working");
    }

}
