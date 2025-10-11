package com.leonardolucs.todolist.controllers;

import com.leonardolucs.todolist.models.dto.TaskDTO;
import com.leonardolucs.todolist.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Void> createTask(@Valid @RequestBody TaskDTO taskDTO, @RequestParam Long userId) {
        taskService.createTask(taskDTO, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks(@RequestParam Long userId) {
        return ResponseEntity.ok(taskService.getAllTasks(userId));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }


}
