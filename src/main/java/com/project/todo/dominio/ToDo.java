package com.project.todo.dominio;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entidade que representa a tabela "to_do".
 * Contém informações principais de uma tarefa.
 */
@Entity
@Table(name = "to_do")
@Getter
@Setter
@NoArgsConstructor
public class ToDo {

    /**
     * Chave primária da tarefa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título da tarefa (sem limite de caracteres, mapeado como VARCHAR(MAX)).
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(MAX)")
    private String titulo;

    /**
     * Descrição da tarefa (opcional, VARCHAR(MAX)).
     */
    @Column(columnDefinition = "VARCHAR(MAX)")
    private String descricao;

    /**
     * Data/hora de cadastro da tarefa.
     */
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    /**
     * Data/hora da última atualização (opcional).
     */
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    /**
     * Identificador do usuário responsável (opcional).
     */
    @Column(name = "id_usuario")
    private Long idUsuario;

    /**
     * Relacionamento com a tabela de "status".
     */
    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "id", nullable = false)
    private Status status;

    /**
     * Prioridade da tarefa (valor inteiro entre 1 e 10).
     */
    @Column(nullable = false)
    private Integer prioridade;

    /**
     * Relacionamento com a tabela de "tipo".
     */
    @ManyToOne
    @JoinColumn(name = "tipo", referencedColumnName = "id", nullable = false)
    private Tipo tipo;
}
