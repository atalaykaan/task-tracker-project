package com.atalaykaan.tasktracker.tasktrackerproject.runner;

import com.atalaykaan.tasktracker.tasktrackerproject.dto.TaskDTO;
import com.atalaykaan.tasktracker.tasktrackerproject.service.TaskService;
import com.atalaykaan.tasktracker.tasktrackerproject.model.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class TaskRunner implements CommandLineRunner {

    private TaskService taskService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public TaskRunner(TaskService taskService) {

        this.taskService = taskService;
    }

    public void run(String... args) {

        demoCreateSingleTask();
        demoUpdateSingleTask();

        demoCreateMultipleTasks();

//        demoDeleteAllTasks();
    }

    private void demoDeleteAllTasks() {

        logger.info("Deleting all tasks...");

        taskService.deleteAllTasks();

        logger.info("Successfully deleted all tasks.");

        logger.info("{}", taskService.findAllTasks());
    }

    private void demoCreateMultipleTasks() {

        logger.info("Creating multiple tasks...");

        TaskDTO task1 = new TaskDTO(null, "Do homework", TaskStatus.TODO);
        TaskDTO task2 = new TaskDTO(null,"Prepare food", TaskStatus.TODO);
        TaskDTO task3 = new TaskDTO(null, "Buy groceries", TaskStatus.TODO);

        logger.info("Saving the tasks...");

        taskService.createTask(task1);
        taskService.createTask(task2);
        taskService.createTask(task3);

        logger.info("Saved tasks: {}", taskService.findAllTasks());

    }

    private void demoUpdateSingleTask() {

        Integer taskId = 1;

        logger.info("Updating task with id: {}", taskId);

        TaskDTO task = new TaskDTO(null, "Learn spring boot", TaskStatus.IN_PROGRESS);

        taskService.updateTask(taskId, task);

        logger.info("Task successfully updated: {}", taskService.findById(taskId));
    }

    private void demoCreateSingleTask() {

        logger.info("Creating a new task...");
        TaskDTO task = new TaskDTO(null, "Buy groceries", TaskStatus.TODO);

        logger.info("Saving the task...");

        TaskDTO newTask = taskService.createTask(task);

        logger.info("Saved task: {}", taskService.findById(newTask.id()));
    }
}
