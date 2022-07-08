package com.codedifferently.taskcli.domain.task.services;

import com.codedifferently.taskcli.domain.task.exception.TaskCreationException;
import com.codedifferently.taskcli.domain.task.exception.TaskNotFoundException;
import com.codedifferently.taskcli.domain.task.models.Task;
import com.codedifferently.taskcli.domain.task.repos.TaskRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService{
    private TaskRepo taskRepo;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo){
        this.taskRepo = taskRepo;
    }

    @Override
    public Task create(Task task) throws TaskCreationException {
        Optional<Task> optional = taskRepo.findByTitle(task.getTitle());
        if(optional.isPresent())
            throw new TaskCreationException("Task already Exist");
        return taskRepo.save(task);
    }

    @Override
    public Task getById(Long id) throws TaskNotFoundException {
        return null;
    }

    @Override
    public String getAllByComplete(Boolean complete) {
        return null;
    }

    @Override
    public String getAll() {
        return null;
    }

    @Override
    public Task updateTitle(Long id, String title) throws TaskNotFoundException {
        Optional<Task> optional = taskRepo.findById(id);
        if(optional.isEmpty())
            throw new TaskNotFoundException("Task with id does not exist "+ id);
        Task task = optional.get();
        task.setTitle(title);
        task = taskRepo.save(task);
        return task;
    }

    @Override
    public Task updateToComplete(Long id) throws TaskNotFoundException {
        Optional<Task> optional = taskRepo.findById(id);
        if(optional.isEmpty())
            throw new TaskNotFoundException("Task with id does not exist "+ id);
        Task task = optional.get();
        task.setComplete(true);
        task = taskRepo.save(task);
        return task;
    }

    @Override
    public Task delete(Long id) throws TaskNotFoundException {
        return null;
    }
}
