package com.atalaykaan.tasktracker.tasktrackerproject.repository;

import com.atalaykaan.tasktracker.tasktrackerproject.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
