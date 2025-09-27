package com.atalaykaan.tasktracker.tasktrackerproject.dto;

import com.atalaykaan.tasktracker.tasktrackerproject.annotation.ValidTaskStatus;
import com.atalaykaan.tasktracker.tasktrackerproject.model.TaskStatus;

public record TaskDTO(
        Integer id,
        String description,
        TaskStatus status
) {
}
