package com.leonardolucs.todolist.services;

import com.leonardolucs.todolist.models.dto.TaskDTO;
import com.leonardolucs.todolist.models.entities.Task;
import com.leonardolucs.todolist.models.entities.Usuario;
import com.leonardolucs.todolist.repositories.TaskRepository;
import com.leonardolucs.todolist.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UsuarioRepository usuarioRepository;
    
    public Task createTask(TaskDTO taskDTO) {
        Usuario usuario = usuarioRepository.findById(taskDTO.getUsuarioId()).orElseThrow(() -> new IllegalArgumentException("Usuário " + taskDTO.getUsuarioId() + " não encontrado"));

        Task task = new Task();
        task.setTitulo(taskDTO.getTitulo());
        task.setDescricao(taskDTO.getDescricao());
//        task.setPrioridade(taskDTO.getPrioridade());
        task.setData(taskDTO.getData());
        task.setHorario(taskDTO.getHorario());
        task.setUsuario(usuario);

        return taskRepository.save(task);
    }
    public TaskDTO createTaskDTO(Task task){
        return new TaskDTO(
                task.getTitulo(),
//                task.getPrioridade(),
                task.getData(),
                task.getHorario(),
                task.getDescricao(),
                task.getUsuario().getId());
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
    public Optional<Task> getTaskById(Long id){
        Optional<Task> task =  taskRepository.findById(id);
        return task;
    }
    public List<TaskDTO> getAllTasks(){

        List<Task> tasks = taskRepository.findAll();
        List<TaskDTO> taskDTO = new ArrayList<>();

        for(Task task : tasks){
            TaskDTO  transformaTaskDTO = createTaskDTO(task);
            taskDTO.add(transformaTaskDTO);
        }
        return taskDTO;
    }

}
