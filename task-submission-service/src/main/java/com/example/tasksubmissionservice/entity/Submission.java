package com.example.tasksubmissionservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long taskId;

    private String githubLink;
    private long userId;

    private String status="PENDING";

    private LocalDateTime submissionTime;


}
