package com.atalaykaan.tasktracker.tasktrackerproject.mapper.impl;

import com.atalaykaan.tasktracker.tasktrackerproject.dto.TaskDTO;
import com.atalaykaan.tasktracker.tasktrackerproject.mapper.TaskMapper;
import com.atalaykaan.tasktracker.tasktrackerproject.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task fromDTO(TaskDTO taskDTO) {

        return new Task(
                taskDTO.id(),
                taskDTO.description(),
                taskDTO.status(),
                null,
                null
        );
    }

    @Override
    public TaskDTO toDTO(Task task) {

        return new TaskDTO(
                task.getId(),
                task.getDescription(),
                task.getStatus()
        );
    }
}
