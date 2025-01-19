package com.project.todo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO para representar uma tarefa.
 */
@Getter
@Setter
@NoArgsConstructor
public class TaskDTO {

    private String titulo;
    private String descricao;
    private LocalDateTime dataCadastro;
    private String status;
    private int prioridade;
    private String tipo;

    public TaskDTO(String titulo, String descricao, LocalDateTime dataCadastro, String status, int prioridade, String tipo) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
        this.status = status;
        this.prioridade = prioridade;
        this.tipo = tipo;
    }

}