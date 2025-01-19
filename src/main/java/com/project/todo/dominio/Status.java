package com.project.todo.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade que representa a tabela "status".
 * Possui um identificador e o nome do status.
 */
@Entity
@Table(name = "status")
@Getter
@Setter
@NoArgsConstructor
public class Status {

    /**
     * Chave primária do status.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nome do status (ex.: "Pendente", "Concluído").
     */
    @Column(nullable = false)
    private String status;
}
