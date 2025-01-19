package com.project.todo.services;

import com.project.todo.dominio.ToDo;
import com.project.todo.repositories.ToDoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Classe de serviço para manipular tarefas (ToDo).
 */
@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    /**
     * Construtor com injeção de dependência do repositório.
     *
     * @param toDoRepository repositório de ToDo
     */
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    /**
     * Cria ou atualiza uma tarefa.
     *
     * @param toDo objeto ToDo
     * @return ToDo salvo
     */
    public ToDo save(ToDo toDo) {
        // Se for uma nova tarefa (id nulo), define dataCadastro agora
        if (toDo.getId() == null) {
            toDo.setDataCadastro(LocalDateTime.now());
        }
        // Atualiza dataAtualizacao sempre que salvar
        toDo.setDataAtualizacao(LocalDateTime.now());

        return toDoRepository.save(toDo);
    }

    /**
     * Retorna todas as tarefas cadastradas.
     *
     * @return lista de ToDo
     */
    public List<ToDo> findAll() {
        return toDoRepository.findAll();
    }

    /**
     * Retorna uma tarefa por ID.
     *
     * @param id identificador da tarefa
     * @return Optional contendo a tarefa ou vazio
     */
    public Optional<ToDo> findById(Long id) {
        return toDoRepository.findById(id);
    }

    /**
     * Exclui uma tarefa pelo ID.
     *
     * @param id identificador da tarefa
     */
    public void deleteById(Long id) {
        toDoRepository.deleteById(id);
    }
}
