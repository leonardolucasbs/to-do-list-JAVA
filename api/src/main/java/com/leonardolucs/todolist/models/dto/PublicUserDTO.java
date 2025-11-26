package com.leonardolucs.todolist.models.dto;

import java.util.List;

public record PublicUserDTO(Long id, String name, String email, List<TaskDTO> tasks) {
}
