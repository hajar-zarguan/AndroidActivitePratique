package com.example.distantdb;

public class Task {

    private Long id;
    private String task;

    public Task(String task) {
        this.task = task;
    }

    public Task(Long id) {
        this.id = id;
    }

    public Task(Long id, String task) {
        this.id = id;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
