package com.codedifferently.taskcli;

import com.codedifferently.taskcli.domain.task.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TaskCliApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskCliApplication.class, args);
    }



}
