package com.example.tasksubmissionservice.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDto {
    private long id;

    private String title;

    private String description;

    private String images;

    private long assignedUserId;

    private List<String> tags = new ArrayList<>();

    private LocalDateTime deadLine;

    private LocalDateTime createdAt;

    private TaskStatus taskStatus;
}
