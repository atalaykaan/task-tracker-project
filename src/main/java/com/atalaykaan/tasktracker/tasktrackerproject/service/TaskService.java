package com.atalaykaan.tasktracker.tasktrackerproject.service;

import com.atalaykaan.tasktracker.tasktrackerproject.exception.TaskNotFoundException;
import com.atalaykaan.tasktracker.tasktrackerproject.model.Task;
import com.atalaykaan.tasktracker.tasktrackerproject.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {

        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks() {

        List<Task> tasks = taskRepository.findAll();

        if(tasks.isEmpty()) {

            throw new TaskNotFoundException("No tasks were found.");
        }

        return tasks;
    }

    public Task findById(int id) {

        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found."));
    }

    @Transactional
    public Task createTask(Task task) {

        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(int id, Task newTask) {

        Task oldTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id " + id + "not found"));

        newTask.setId(id);
        newTask.setCreatedAt(oldTask.getCreatedAt());
        return taskRepository.save(newTask);
    }

    @Transactional
    public void deleteTask(int id) {

        taskRepository.delete(taskRepository
                .findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id " + id + "not found")));
    }

    @Transactional
    public void deleteAllTasks() {

        taskRepository.deleteAll();
    }
}
