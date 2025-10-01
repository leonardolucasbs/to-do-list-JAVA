package com.leonardolucs.todolist.repositories;

import com.leonardolucs.todolist.models.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
