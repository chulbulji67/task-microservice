package com.example.taskservice.service;

import com.example.taskservice.entity.Task;
import com.example.taskservice.entity.TaskStatus;
import com.example.taskservice.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    TaskRepo taskRepo;

    @Override
    public Task createTask(Task task, String requesterRole) throws Exception {
        if(!requesterRole.equals("ROLE_ADMIN")){
            throw new Exception("only adin can create task");

        }
        task.setTaskStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());

        return taskRepo.save(task);
    }

    @Override
    public Task getTaskById(long id) throws Exception {
        return taskRepo.findById(id).orElseThrow(()-> new Exception("Task not found with id"+id));
    }

    @Override
    public List<Task> getAllTasks(TaskStatus status) {
        List<Task> allTasks = taskRepo.findAll();
        List<Task> filterdTask = allTasks.stream().filter(
                task -> status==null || task.getTaskStatus().name().equalsIgnoreCase(status.toString())

        ).collect(Collectors.toList());

        return filterdTask;
    }

    @Override
    public Task updateTask(Long id, Task updateTask, long userId) throws Exception {
        Task existingTask = getTaskById(id);
    if(updateTask.getTitle()!=null){
        existingTask.setTitle(updateTask.getTitle());
    }
    if(updateTask.getImages() != null){
        existingTask.setImages(updateTask.getImages());
    }
    if(updateTask.getTaskStatus() != null){
        existingTask.setTaskStatus(existingTask.getTaskStatus());

    }
    if(updateTask.getDeadLine() != null){
        existingTask.setDeadLine(existingTask.getDeadLine());
    }
    if(updateTask.getDescription() != null){
        existingTask.setDescription(updateTask.getDescription());
    }
        return taskRepo.save(existingTask);
    }

    @Override
    public void deleteTask(long id) throws Exception {
        Task task = getTaskById(id);
        if(task == null){
            throw new Exception("Task Not found Exception");
        }
        taskRepo.deleteById(id);
    }


    @Override
    public Task assignedToUser(long userId, long taskId) throws Exception {
        Task existingTask = getTaskById(taskId);
        existingTask.setAssignedUserId(userId);
        existingTask.setTaskStatus(TaskStatus.DONE);
        return taskRepo.save(existingTask);
    }

    @Override
    public List<Task> assignedUserTask(Long userId, TaskStatus status) {
        List<Task> allTasks = taskRepo.findByAssignedUserId(userId);
        return allTasks.stream().filter(task -> status == null || task.getTaskStatus().name().equalsIgnoreCase(status.toString())).toList();
    }

    @Override
    public Task completeTask(long taskId) throws Exception {
        Task task  = getTaskById(taskId);
        task.setTaskStatus(TaskStatus.DONE);
        return taskRepo.save(task);
    }
}
