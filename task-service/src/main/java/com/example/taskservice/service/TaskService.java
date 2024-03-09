package com.example.taskservice.service;

import com.example.taskservice.entity.Task;
import com.example.taskservice.entity.TaskStatus;

import java.util.List;

public interface TaskService {

    Task createTask(Task task, String requesterRole) throws Exception;

    Task getTaskById(long id) throws Exception;

    List<Task> getAllTasks(TaskStatus status);

    Task updateTask(Long id, Task updateTask, long userId) throws Exception;

    void deleteTask(long id) throws Exception;

    Task assignedToUser(long userIdk, long taskId) throws  Exception;

    List<Task> assignedUserTask(Long userId, TaskStatus status);

    Task completeTask(long taskId) throws Exception;
}
