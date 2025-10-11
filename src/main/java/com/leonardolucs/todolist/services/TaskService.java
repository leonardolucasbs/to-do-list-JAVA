package com.leonardolucs.todolist.services;

import com.leonardolucs.todolist.models.dto.TaskDTO;
import com.leonardolucs.todolist.models.entities.Labels;
import com.leonardolucs.todolist.models.entities.Task;
import com.leonardolucs.todolist.models.entities.User;
import com.leonardolucs.todolist.repositories.LabelsRepository;
import com.leonardolucs.todolist.repositories.TaskRepository;
import com.leonardolucs.todolist.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final LabelsRepository labelsRepository;

    public Task createTask(TaskDTO taskDTO) {
        User user = userRepository.findById(taskDTO.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<Labels> labels = new HashSet<>();
        if (taskDTO.labelIds() != null && !taskDTO.labelIds().isEmpty()) {
            labels = new HashSet<>(labelsRepository.findAllById(taskDTO.labelIds()));
        }

        Task task = new Task();
        task.setTitle(taskDTO.title());
        task.setDescription(taskDTO.description());
        task.setDate(taskDTO.date());
        task.setTime(taskDTO.time());
        task.setUser(user);
        task.setLabels(labels);

        return taskRepository.save(task);
    }

    public TaskDTO createTaskDTO(Task task){
        Set<Long> labelIds = task.getLabels() != null ? task.getLabels().stream()
                .map(Labels::getId)
                .collect(Collectors.toSet()) : new HashSet<>();

        return new TaskDTO(
                task.getTitle(),
                task.getDate(),
                task.getTime(),
                task.getDescription(),
                task.getUser().getId(),
                labelIds
        );
    }

    public ResponseEntity<?> getTaskById(Long id){
        Optional<Task> foudTask = taskRepository.findById(id);
        return foudTask.isEmpty()
                ? ResponseEntity.badRequest().body("User not registered.")
                : ResponseEntity.ok(createTaskDTO(foudTask.get()));

    }

    public List<TaskDTO> getAllTasks(){
        return taskRepository.findAll()
                .stream()
                .map(this::createTaskDTO)
                .collect(Collectors.toList());
    }
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
