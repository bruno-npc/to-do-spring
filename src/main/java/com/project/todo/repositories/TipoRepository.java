package com.project.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.todo.dominio.Tipo;

/**
 * Repositório para a entidade Tipo.
 */
public interface TipoRepository extends JpaRepository<Tipo, Integer> {

}