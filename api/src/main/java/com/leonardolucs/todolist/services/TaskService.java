package com.leonardolucs.todolist.services;

import com.leonardolucs.todolist.models.dto.CreateTaskDTO;
import com.leonardolucs.todolist.models.dto.TaskDTO;
import com.leonardolucs.todolist.models.entities.Description;
import com.leonardolucs.todolist.models.entities.Label;
import com.leonardolucs.todolist.models.entities.Task;
import com.leonardolucs.todolist.models.entities.User;
import com.leonardolucs.todolist.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final DescriptionService descriptionService;
    private final UserService userService;
    private final LabelsService labelsService;

    public Task createTask(CreateTaskDTO taskDTO, Long userId) {
        User user = userService.findEntityById(userId);
        Task newTask = new Task();
        newTask.setTitle(taskDTO.title());
        newTask.setDateTime(taskDTO.dateTime());
        newTask.setUser(user);

        if (taskDTO.labelId() != null && !taskDTO.labelId().isEmpty()) {
            List<Label> foundLabels = new ArrayList<>();
            for (Long label : taskDTO.labelId()) {
                Optional<Label> foudLabel = labelsService.getLabelById(label);
                foudLabel.ifPresent(foundLabels::add);
                newTask.setLabels(foundLabels);

            }
            taskRepository.save(newTask);

            Description description = descriptionService.createDescription(taskDTO.description(), newTask);
            newTask.setDescription(description);
        }
        return taskRepository.save(newTask);
    }

    public TaskDTO createTaskDTO(Task task) {
        List<Long> LabelsToIds = new ArrayList<>();

        for (Label label : task.getLabels()) {
            LabelsToIds.add(label.getId());
        }

        return task.toDto();
    }


    public ResponseEntity<?> getTaskById(Long id) {
        Optional<Task> foudTask = taskRepository.findById(id);
        return foudTask.isEmpty()
                ? ResponseEntity.badRequest().body("User not registered.")
                : ResponseEntity.ok(createTaskDTO(foudTask.get()));

    }

    public List<TaskDTO> getAllTasks(Long userId) {
        User user = userService.findEntityById(userId);

        return user.getTasks()
                .stream()
                .map(this::createTaskDTO)
                .collect(Collectors.toList());
    }
//    public  ResponseEntity<?> updateTask(Long taskId, Long id){
//        Optional<Task> foundTask = taskRepository.findById(id);
//        if(foundTask.isEmpty()){
//            return ResponseEntity.badRequest().body("Task not found.");
//        }
//        Task task = new Task();
//        task.setTitle(taskDTO.title());
//        task.setDateTime(taskDTO.dateTime());
//        taskRepository.save(task);
//
//        return ResponseEntity.ok(createTaskDTO(task));
//
//    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
