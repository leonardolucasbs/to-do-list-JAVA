package com.leonardolucs.todolist.models.dto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter @Setter
@Data @AllArgsConstructor @NoArgsConstructor
public class TaskDTO { // trocar para record
    private String titulo;
//    private String prioridade;
    private LocalDate data;
    private LocalTime horario;
    private String descricao;
    private Long usuarioId ; // trocar para UsuarioDTO
}
