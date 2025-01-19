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
 * Entidade que representa a tabela "tipo".
 * Possui um identificador e a descrição do tipo.
 */
@Entity
@Table(name = "tipo")
@Getter
@Setter
@NoArgsConstructor
public class Tipo {

    /**
     * Chave primária do tipo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Descrição do tipo (ex.: "Rotina", "Urgente", "Projeto").
     */
    @Column(nullable = false)
    private String tipo;
}
