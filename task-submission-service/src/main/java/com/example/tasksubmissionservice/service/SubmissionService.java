package com.example.tasksubmissionservice.service;

import com.example.tasksubmissionservice.entity.Submission;

import java.util.List;

public interface SubmissionService {
    Submission sumbitTask(long taskId, String githubLink, long userId, String jwt) throws Exception;

    Submission getTaskSubmissionByID(long submissionId) throws Exception;

    List<Submission> getAllTaskSubmissions();

    List<Submission> getTaskSubmissionByTaskId(long taskId);

    Submission acceptDeclineSubmission(long id, String status) throws Exception;
}
