package com.atalaykaan.tasktracker.tasktrackerproject.service;

import com.atalaykaan.tasktracker.tasktrackerproject.dto.TaskDTO;
import com.atalaykaan.tasktracker.tasktrackerproject.exception.TaskNotFoundException;
import com.atalaykaan.tasktracker.tasktrackerproject.mapper.TaskMapper;
import com.atalaykaan.tasktracker.tasktrackerproject.model.Task;
import com.atalaykaan.tasktracker.tasktrackerproject.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    private TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {

        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<TaskDTO> findAllTasks() {

        return Optional.of(taskRepository.findAll())
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new TaskNotFoundException("No tasks were found"))
                .stream()
                .map(taskMapper::toDTO)
                .toList();

//        if(tasks.isEmpty()) {
//
//            throw new TaskNotFoundException("No tasks were found.");
//        }
//
//        List<TaskDTO> taskDTOList = tasks.stream()
//                .map(taskMapper::toDTO)
//                .toList();
//
//        return taskDTOList;
    }

    public TaskDTO findById(int id) {

        return taskRepository.findById(id).map(taskMapper::toDTO)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found."));
    }

    @Transactional
    public TaskDTO createTask(TaskDTO taskDTO) {

        Task task = taskRepository.save(taskMapper.fromDTO(taskDTO));

        return taskMapper.toDTO(task);
    }

    @Transactional
    public TaskDTO updateTask(int id, TaskDTO taskDTO) {

        Task oldTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));

        Task task = taskMapper.fromDTO(taskDTO);
        task.setId(id);
        task.setCreatedAt(oldTask.getCreatedAt());
        Task savedTask = taskRepository.save(task);

        return taskMapper.toDTO(savedTask);
    }

    @Transactional
    public void deleteTask(int id) {

        taskRepository.delete(taskRepository
                .findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found")));
    }

    @Transactional
    public void deleteAllTasks() {

        taskRepository.deleteAll();
    }
}
