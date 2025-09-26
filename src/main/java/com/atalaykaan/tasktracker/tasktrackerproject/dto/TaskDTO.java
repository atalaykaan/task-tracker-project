package com.atalaykaan.tasktracker.tasktrackerproject.dto;

import com.atalaykaan.tasktracker.tasktrackerproject.model.TaskStatus;

public record TaskDTO(
        int id,
        String description,
        TaskStatus status
) {
}
