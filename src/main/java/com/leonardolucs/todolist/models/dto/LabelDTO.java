package com.leonardolucs.todolist.models.dto;

import jakarta.validation.constraints.NotBlank;

public record LabelDTO(
        Long id,
        @NotBlank(message = "The label name cannot be blank.")
        String name
) {
}

