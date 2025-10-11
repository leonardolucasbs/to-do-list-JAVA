package com.leonardolucs.todolist.services;

import com.leonardolucs.todolist.models.dto.TaskDTO;
import com.leonardolucs.todolist.models.entities.Description;
import com.leonardolucs.todolist.models.entities.Label;
import com.leonardolucs.todolist.models.entities.Task;
import com.leonardolucs.todolist.models.entities.User;
import com.leonardolucs.todolist.repositories.LabelRepository;
import com.leonardolucs.todolist.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final LabelRepository labelRepository;
    private final DescriptionService descriptionService;
    private final UserService userService;

    public Task createTask(TaskDTO taskDTO, Long userId) {
        User user = userService.findEntityById(userId);

        Set<Label> labels = new HashSet<>();
        if (taskDTO.label() != null && !taskDTO.label().isEmpty()) {
            labels = new HashSet<>(labelRepository.findAllById(taskDTO.label()));
        }

        Task task = new Task();
        task.setTitle(taskDTO.title());
        task.setDateTime(taskDTO.dateTime());
        task.setUser(user);
        task.setLabels(labels);
        taskRepository.save(task);

        Description description = descriptionService.createDescription(taskDTO.description(), task);
        task.setDescription(description);

        return taskRepository.save(task);

    }

    public TaskDTO createTaskDTO(Task task){
        Set<Long> idsDosLabels = new HashSet<>();
        for (Label label : task.getLabels()) {
            idsDosLabels.add(label.getId());
        }

        return task.toDto();
    }


    public ResponseEntity<?> getTaskById(Long id){
        Optional<Task> foudTask = taskRepository.findById(id);
        return foudTask.isEmpty()
                ? ResponseEntity.badRequest().body("User not registered.")
                : ResponseEntity.ok(createTaskDTO(foudTask.get()));

    }

    public List<TaskDTO> getAllTasks(Long userId){
        User user = userService.findEntityById(userId);

        return user.getTasks()
                .stream()
                .map(this::createTaskDTO)
                .collect(Collectors.toList());
    }
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
