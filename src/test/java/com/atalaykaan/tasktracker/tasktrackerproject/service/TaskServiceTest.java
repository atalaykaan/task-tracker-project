package com.atalaykaan.tasktracker.tasktrackerproject.service;

import com.atalaykaan.tasktracker.tasktrackerproject.dto.TaskDTO;
import com.atalaykaan.tasktracker.tasktrackerproject.exception.InvalidTaskPostRequestException;
import com.atalaykaan.tasktracker.tasktrackerproject.mapper.TaskMapper;
import com.atalaykaan.tasktracker.tasktrackerproject.model.Task;
import com.atalaykaan.tasktracker.tasktrackerproject.model.TaskStatus;
import com.atalaykaan.tasktracker.tasktrackerproject.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void whenCreatingTaskUsingDtoWithId_itShouldThrowInvalidTaskPostRequestException() {

        TaskDTO taskDTO = new TaskDTO(1, "TestDescription", TaskStatus.TODO);

        assertThrows(InvalidTaskPostRequestException.class, () -> taskService.createTask(taskDTO));
    }

    @Test
    public void whenCreatingTaskUsingDtoWithNullId_itShouldReturnDtoBack() {

        TaskDTO inputTaskDTO = new TaskDTO(null, "TestDescription", TaskStatus.TODO);

        TaskDTO expectedTaskDTO = new TaskDTO(1, "TestDescription", TaskStatus.TODO);

        Task inputTask = new Task(null,
                "TestDescription",
                TaskStatus.TODO,
                null,
                null);

        Task savedTask = new Task(1,
                "TestDescription",
                TaskStatus.TODO,
                LocalDateTime.now(),
                LocalDateTime.now());

        Mockito.when(taskMapper.fromDTO(inputTaskDTO)).thenReturn(inputTask);
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(savedTask);
        Mockito.when(taskMapper.toDTO(savedTask)).thenReturn(expectedTaskDTO);

        TaskDTO result = taskService.createTask(inputTaskDTO);

        assertEquals(expectedTaskDTO, result);

    }

}