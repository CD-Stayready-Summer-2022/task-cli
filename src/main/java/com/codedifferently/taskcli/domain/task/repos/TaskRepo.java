package com.codedifferently.taskcli.domain.task.repos;

import com.codedifferently.taskcli.domain.task.models.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskRepo extends CrudRepository<Task, Long> {
    Iterable<Task> findAllByComplete(Boolean complete);
    Optional<Task> findByTitle(String title);
}
