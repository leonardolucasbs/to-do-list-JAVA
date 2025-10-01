package com.leonardolucs.todolist.models.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record TaskRecord( String titulo,
         String prioridade,
         LocalDate data,
         LocalTime horario,
         String descricao,
         Long usuarioId ) {
}
