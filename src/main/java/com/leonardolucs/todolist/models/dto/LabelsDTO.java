package com.leonardolucs.todolist.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelsDTO {

    @NotBlank(message = "The label name cannot be blank.")
    private String name;
}
