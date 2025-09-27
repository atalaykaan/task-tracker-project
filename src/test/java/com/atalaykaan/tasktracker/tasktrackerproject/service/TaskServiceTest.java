package com.atalaykaan.tasktracker.tasktrackerproject.service;

import com.atalaykaan.tasktracker.tasktrackerproject.dto.TaskDTO;
import com.atalaykaan.tasktracker.tasktrackerproject.exception.InvalidTaskPostRequestException;
import com.atalaykaan.tasktracker.tasktrackerproject.model.Task;
import com.atalaykaan.tasktracker.tasktrackerproject.model.TaskStatus;
import com.atalaykaan.tasktracker.tasktrackerproject.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void whenCreatingTaskUsingDtoWithId_itShouldThrowInvalidTaskPostRequestException() {

        TaskDTO taskDTO = new TaskDTO(1, "TestDescription", TaskStatus.TODO);

        Mockito.when(taskService.createTask(taskDTO)).thenThrow(InvalidTaskPostRequestException.class);

        assertThrows(InvalidTaskPostRequestException.class, () -> taskService.createTask(taskDTO));
    }

//    @Test
//    public void whenCreatingTaskUsingDtoWithNullId_itShouldReturnDtoBack() {
//
//        Task task = new Task(1,
//                "TestDescription",
//                TaskStatus.TODO,
//                LocalDateTime.now(),
//                LocalDateTime.now());
//
//        TaskDTO taskDTO = new TaskDTO(1, "TestDescription", TaskStatus.TODO);
//
//        Mockito.when(taskRepository.save(task)).thenReturn(task);
//
//        TaskDTO result = taskService.createTask(taskDTO);
//
//        assertEquals(result, taskDTO);
//    }

}