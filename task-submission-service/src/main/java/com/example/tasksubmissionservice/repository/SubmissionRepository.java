package com.example.tasksubmissionservice.repository;

import com.example.tasksubmissionservice.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByTaskId(long taskId);
}
