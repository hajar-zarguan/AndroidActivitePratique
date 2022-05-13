package com.example.tasksandroid.Repositories;

import com.example.tasksandroid.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Task,Long> {

}
