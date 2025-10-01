package com.leonardolucs.todolist.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.leonardolucs.todolist.models.Prioridade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private LocalTime horario;
//    private Prioridade prioridade;
    private LocalDate data;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    /*
    1 task = 1 descricao resolve com fk
    n tasks = 1 usuario resolve com fk
    n task = n labels cria uma nova tabela


    task = (id = 1, titulo = estudar, descID = 1,  usuarioID = 1)
    task2 = (id = 2, titulo = malhar, descID = 2, usuarioID = 1)
    task3 = (id = 3, titulo = estudar, descID = 3, usuarioID = 2)

    descricao = (id = 1, descricao = estudar java)
    descricao2 = (id = 2, descricao = malhar fullbody)
    descricao3 = (id = 3, descricao = estudar angular)

    usuario = (id = 1, nome = leo)
    usuario2 = (id = 2, nome = jose)

    label1 = (id = 1, nome = Alta)
    label2 = (id = 2, nome = Fazendo)
    label3 = (id = 3, nome = Baixa)
    label4 = (id = 4, nome = feito)

    tabela para associacao
    assoc_task_label = (taskID = 1, labelID = 1)
    assoc_task_label = (taskID = 1, labelID = 2)
    assoc_task_label = (taskID = 2, labelID = 1)
    assoc_task_label = (taskID = 2, labelID = 4)
    assoc_task_label = (taskID = 3, labelID = 3)
    assoc_task_label = (taskID = 3, labelID = 2)


     */
}

