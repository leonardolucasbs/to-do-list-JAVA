package com.leonardolucs.todolist.models.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

public record TaskDTO(
        @NotBlank(message = "The title cannot be blank.")
        String title,

        @NotNull(message = "The date cannot be null.")
        @FutureOrPresent(message = "The task date cannot be in the past.")
        LocalDateTime dateTime,

        String description,

        Set<Long> labelIds

) {
}
