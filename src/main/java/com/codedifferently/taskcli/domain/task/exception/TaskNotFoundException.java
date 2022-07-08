package com.codedifferently.taskcli.domain.task.exception;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(String message) {
        super(message);
    }
}
