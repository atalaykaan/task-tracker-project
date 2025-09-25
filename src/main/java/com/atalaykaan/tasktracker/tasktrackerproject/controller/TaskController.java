package com.atalaykaan.tasktracker.tasktrackerproject.controller;

import com.atalaykaan.tasktracker.tasktrackerproject.model.Task;
import com.atalaykaan.tasktracker.tasktrackerproject.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {

        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {

        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {

        return ResponseEntity.ok(taskService.findById(id));
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(@Valid @RequestBody Task task) {

        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task));
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@Valid @PathVariable int id, @RequestBody Task task) {

        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable int id) {

        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/tasks")
    public ResponseEntity<Task> deleteAllTasks() {

        taskService.deleteAllTasks();

        return ResponseEntity.noContent().build();
    }
}
