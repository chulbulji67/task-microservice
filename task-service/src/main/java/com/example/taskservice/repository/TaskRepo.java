package com.example.taskservice.repository;

import com.example.taskservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    public List<Task> findByAssignedUserId(long userId);
}
