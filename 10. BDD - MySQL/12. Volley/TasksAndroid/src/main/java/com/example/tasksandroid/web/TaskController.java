package com.example.tasksandroid.web;

import com.example.tasksandroid.Repositories.TasksRepository;

import com.example.tasksandroid.entities.Task;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class TaskController {
    TasksRepository tasksRepository;
    @GetMapping("/")
    List<Task> all() {
        return tasksRepository.findAll();
    }
    @PostMapping("/")
    Task newTask(@RequestBody Task newEmployee) {
        return tasksRepository.save(newEmployee);
    }
    @PutMapping("/{id}")
    Task replaceTask(@RequestBody Task newTask, @PathVariable Long id) {
         Task task = tasksRepository.findById(id).orElse(null);
         if(task!=null){
             task.setTask(newTask.getTask());
             tasksRepository.save(task);
         }
        return task;
    }
    @PatchMapping("/{id}")
    Task replaceT(@RequestBody Task newTask, @PathVariable Long id) {
        Task task = tasksRepository.findById(id).orElse(null);
        if(task!=null){
            task.setTask(newTask.getTask());
            tasksRepository.save(task);
        }
        return task;
    }
    @DeleteMapping("/{id}")
    Task deleteTask(@PathVariable Long id) {
        Task task = tasksRepository.findById(id).orElse(null);
        tasksRepository.deleteById(id);
        return task;
    }

}
