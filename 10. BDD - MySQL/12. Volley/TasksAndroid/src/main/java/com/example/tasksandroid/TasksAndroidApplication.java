package com.example.tasksandroid;

import com.example.tasksandroid.Repositories.TasksRepository;
import com.example.tasksandroid.entities.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TasksAndroidApplication {

    public static void main(String[] args) {
        SpringApplication.run(TasksAndroidApplication.class, args);

    }
  // @Bean
    CommandLineRunner commandLineRunner(TasksRepository tasksRepository){
        return args ->{
            tasksRepository.save(new Task(null,"Présentation"));
            tasksRepository.save(new Task(null,"Modélisation"));
            tasksRepository.save(new Task(null,"Réalisation"));
            tasksRepository.save(new Task(null,"Implémentation"));
        };
    }



}
