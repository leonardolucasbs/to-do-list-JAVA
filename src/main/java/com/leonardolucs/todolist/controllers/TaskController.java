package com.leonardolucs.todolist.controllers;

import com.leonardolucs.todolist.models.dto.PublicUserDTO;
import com.leonardolucs.todolist.models.dto.TaskDTO;
import com.leonardolucs.todolist.models.entities.Task;
import com.leonardolucs.todolist.models.entities.User;
import com.leonardolucs.todolist.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody  TaskDTO taskDTO) {
        Task task = taskService.createTask(taskDTO); //devolver TaskDTO, nunca voltar a entidade original
        return ResponseEntity.status(201).body(task);
    }
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());

    }
   @GetMapping("/{id}")
   public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id){

   }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.status(204).build();
    }


}
