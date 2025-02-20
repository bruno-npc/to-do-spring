package com.project.todo.controllers;

import com.project.todo.dominio.ToDo;
import com.project.todo.dto.TaskDTO;
import com.project.todo.services.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para a entidade ToDo (tarefas).
 */
@RestController
@RequestMapping("/todo")
public class ToDoController {

    private final ToDoService toDoService;

    /**
     * Construtor com injeção de dependência do serviço de ToDo.
     *
     * @param toDoService service para manipular tarefas
     */
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    /**
     * Cria ou atualiza uma tarefa.
     *
     * @param toDo objeto ToDo no corpo da requisição
     * @return tarefa criada/atualizada
     */
    @PostMapping
    public ResponseEntity<ToDo> createOrUpdate(@RequestBody ToDo toDo) {
        ToDo saved = toDoService.save(toDo);
        return ResponseEntity.ok(saved);
    }

    /**
     * Retorna todas as tarefas.
     *
     * @return lista de ToDo
     */
    @GetMapping("/all")
    public ResponseEntity<List<ToDo>> getAll() {
        return ResponseEntity.ok(toDoService.findAll());
    }

    /**
     * Retorna uma tarefa específica pelo ID.
     *
     * @param id identificador da tarefa
     * @return tarefa encontrada, ou 404 se não existir
     */
    @GetMapping
    public ResponseEntity<ToDo> getById(@RequestParam(name = "id", required = false) Long id) {
        return toDoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Exclui uma tarefa pelo ID.
     *
     * @param id identificador da tarefa
     * @return HTTP 204 em caso de sucesso
     */
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(name = "id") Long id) {
        Optional<ToDo> optionalToDo = toDoService.findById(id);
        if (optionalToDo.isPresent()) {
            toDoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retorna todas as tarefas como DTOs.
     */
    @GetMapping("/allresume")
    public ResponseEntity<List<TaskDTO>> getAllTasksAsDTO() {
        List<TaskDTO> tasks = toDoService.findAllTasksAsDTO();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Retorna uma tarefa específica pelo ID como DTO.
     */
    @GetMapping("/resume")
    public ResponseEntity<TaskDTO> getTaskByIdAsDTO(@RequestParam(name = "id") Long id) {
        return toDoService.findTaskByIdAsDTO(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}