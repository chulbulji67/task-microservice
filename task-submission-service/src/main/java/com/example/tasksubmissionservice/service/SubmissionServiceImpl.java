package com.example.tasksubmissionservice.service;

import com.example.tasksubmissionservice.dto.TaskDto;
import com.example.tasksubmissionservice.entity.Submission;
import com.example.tasksubmissionservice.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService{
    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Override
    public Submission sumbitTask(long taskId, String githubLink, long userId, String jwt) throws Exception {
        TaskDto task = taskService.getTaskById(taskId, jwt);
        if(task != null){
            Submission submission = new Submission();
            submission.setTaskId(taskId);
            submission.setUserId(userId);
            submission.setGithubLink(githubLink);
            submission.setSubmissionTime(LocalDateTime.now());
            return submissionRepository.save(submission);
        } throw new Exception("Task Not found with id "+taskId);
    }

    @Override
    public Submission getTaskSubmissionByID(long submissionId) throws Exception{
        return submissionRepository.findById(submissionId).orElseThrow(()->
                new Exception("Task Submission not found with id"));
    }

    @Override
    public List<Submission> getAllTaskSubmissions() {
        return submissionRepository.findAll();
    }

    @Override
    public List<Submission> getTaskSubmissionByTaskId(long taskId) {
        return submissionRepository.findByTaskId(taskId);
    }

    @Override
    public Submission acceptDeclineSubmission(long id, String status) throws Exception {
        Submission submission = getTaskSubmissionByID(id);
        submission.setStatus(status);
        if(status.equals("ACCEPT")){
            taskService.completeTask(submission.getTaskId());
        }

        return submissionRepository.save(submission);
    }
}
