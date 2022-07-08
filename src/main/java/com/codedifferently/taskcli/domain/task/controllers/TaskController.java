package com.codedifferently.taskcli.domain.task.controllers;

import com.codedifferently.taskcli.domain.task.exception.TaskCreationException;
import com.codedifferently.taskcli.domain.task.exception.TaskNotFoundException;
import com.codedifferently.taskcli.domain.task.models.Task;
import com.codedifferently.taskcli.domain.task.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ShellMethod(value = "New task", key="new-task")
    public Task createNewTask(@ShellOption({"-T", "--title"})String title) throws TaskCreationException {
        Task task = new Task(title);
        task = taskService.create(task);
        return task;
    }

    @ShellMethod(value="Update", key="up-task")
    public Task updateTitle(@ShellOption({"-I", "--id"}) Long id,
                            @ShellOption({"-T", "--title"})String title) throws TaskNotFoundException {
        Task task = taskService.updateTitle(id, title);
        return task;
    }
}
