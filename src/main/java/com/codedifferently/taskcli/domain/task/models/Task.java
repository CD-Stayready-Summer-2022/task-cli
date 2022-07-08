package com.codedifferently.taskcli.domain.task.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Boolean complete;

    public Task(){
        this.complete = false;
    }

    public Task(String title){
        this.title = title;
        this.complete = false;
    }

    @Override
    public String toString(){
        return String.format("%d %s %b",id, title, complete);
    }

}
