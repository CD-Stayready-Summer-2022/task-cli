package com.codedifferently.taskcli.domain.task.services;

import com.codedifferently.taskcli.domain.task.exception.TaskCreationException;
import com.codedifferently.taskcli.domain.task.exception.TaskNotFoundException;
import com.codedifferently.taskcli.domain.task.models.Task;

public interface TaskService {
    Task create(Task task) throws TaskCreationException;
    Task getById(Long id) throws TaskNotFoundException;
    String getAllByComplete(Boolean complete);
    String getAll();
    Task updateTitle(Long id, String title) throws TaskNotFoundException;
    Task updateToComplete(Long id) throws TaskNotFoundException;
    Task delete(Long id) throws TaskNotFoundException;
}
