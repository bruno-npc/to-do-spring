package com.project.todo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.project.todo.dominio.ToDo;
import com.project.todo.dto.TaskDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repositório para a entidade ToDo (tabela to_do).
 */
public interface ToDoRepository extends JpaRepository<ToDo, Long> {


    @Query("SELECT t.titulo, t.descricao, t.dataCadastro, s.status, t.prioridade, tp.tipo " +
            "FROM ToDo t " +
            "JOIN t.status s " +
            "JOIN t.tipo tp")
    List<Object[]> findAllTasksAsObject();

    @Query("""
    SELECT new com.project.todo.dto.TaskDTO(
        t.titulo,
        t.descricao,
        t.dataCadastro,
        s.status,
        t.prioridade,
        tp.tipo
    )
    FROM ToDo t
    JOIN t.status s
    JOIN t.tipo tp
    WHERE t.id = :id
""")
    Optional<TaskDTO> findTaskByIdAsDTO(@Param("id") Long id);


}