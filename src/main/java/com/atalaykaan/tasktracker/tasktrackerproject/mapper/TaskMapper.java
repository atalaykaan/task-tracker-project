package com.atalaykaan.tasktracker.tasktrackerproject.mapper;

import com.atalaykaan.tasktracker.tasktrackerproject.dto.TaskDTO;
import com.atalaykaan.tasktracker.tasktrackerproject.model.Task;

public interface TaskMapper {

    public Task fromDTO(TaskDTO taskDTO);

    public TaskDTO toDTO(Task task);
}
