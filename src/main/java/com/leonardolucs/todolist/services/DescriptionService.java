package com.leonardolucs.todolist.services;

import com.leonardolucs.todolist.models.entities.Description;
import com.leonardolucs.todolist.models.entities.Task;
import com.leonardolucs.todolist.repositories.DescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DescriptionService {
    private final DescriptionRepository descriptionRepository;

    public Description createDescription(String description, Task task){
       return descriptionRepository.save(new Description(description, task));
    }
}
