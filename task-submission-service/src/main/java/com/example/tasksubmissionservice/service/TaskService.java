package com.example.tasksubmissionservice.service;

import com.example.tasksubmissionservice.dto.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "SUBMISSION-SERVICE", url = "http://localhost:9092/")
public interface TaskService {
    @GetMapping("api/tasks/{id}")
    public TaskDto getTaskById(@PathVariable long id , @RequestHeader("Authorization")String jwt) throws Exception;

    @PutMapping("api/tasks/{id}/complete")
    public TaskDto completeTask(@PathVariable long id) throws Exception;
}
