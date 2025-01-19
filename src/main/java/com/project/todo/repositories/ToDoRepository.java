package com.project.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.todo.dominio.ToDo;

/**
 * Repositório para a entidade ToDo (tabela to_do).
 */
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

}