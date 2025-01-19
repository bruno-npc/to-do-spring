package com.project.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.todo.dominio.Status;

/**
 * Repositório para a entidade Status.
 */
public interface StatusRepository extends JpaRepository<Status, Integer> {

}