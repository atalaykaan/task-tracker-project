package com.atalaykaan.tasktracker.tasktrackerproject.runner;

import com.atalaykaan.tasktracker.tasktrackerproject.model.Task;
import com.atalaykaan.tasktracker.tasktrackerproject.service.TaskService;
import com.atalaykaan.tasktracker.tasktrackerproject.model.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public class TaskRunner implements CommandLineRunner {

    private TaskService taskService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public TaskRunner(TaskService taskService) {

        this.taskService = taskService;
    }

    public void run(String... args) {

        demoCreateSingleTask(taskService);
        demoUpdateSingleTask(taskService);

        demoCreateMultipleTasks(taskService);

        demoDeleteAllTasks(taskService);
    }

    private void demoDeleteAllTasks(TaskService taskService) {

        logger.info("Deleting all tasks...");

        taskService.deleteAllTasks();

        logger.info("Successfully deleted all tasks.");

        logger.info("{}", taskService.findAllTasks());
    }

    private void demoCreateMultipleTasks(TaskService taskService) {

        logger.info("Creating multiple tasks...");

        Task task1 = new Task("Do homework", TaskStatus.TODO);
        Task task2 = new Task("Prepare food", TaskStatus.TODO);
        Task task3 = new Task("Buy groceries", TaskStatus.TODO);

        logger.info("Saving the tasks...");

        taskService.createTask(task1);
        taskService.createTask(task2);
        taskService.createTask(task3);

        logger.info("Saved tasks: {}", taskService.findAllTasks());

    }

    private void demoUpdateSingleTask(TaskService taskService) {

        int taskId = 1;

        logger.info("Updating task with id: {}", taskId);

        Task task = new Task("Learn spring boot", TaskStatus.IN_PROGRESS);

        taskService.updateTask(taskId, task);

        logger.info("Task successfully updated: {}", taskService.findById(taskId));
    }

    private void demoCreateSingleTask(TaskService taskService) {

        logger.info("Creating a new task...");
        Task task = new Task("Buy groceries", TaskStatus.TODO);

        logger.info("Saving the task...");
        taskService.createTask(task);

        logger.info("Saved task: {}", taskService.findById(task.getId()));
    }
}
