package com.leonardolucs.todolist.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leonardolucs.todolist.models.dto.TaskDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToMany
    @JoinTable(
            name = "task_labels",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private List<Label> labels;

    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL)
    private Description description;

    public TaskDTO toDto(){
        return new TaskDTO(
                title,
                dateTime,
                description == null ? "" : description.getDescription(),
                labels
        );
    }
}
