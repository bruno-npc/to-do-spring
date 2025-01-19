package com.project.todo.controllers;

import com.project.todo.dominio.Status;
import com.project.todo.services.StatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para a entidade Status.
 */
@RestController
@RequestMapping("/todo/status")
public class StatusController {

    private final StatusService statusService;

    /**
     * Construtor com injeção de dependência do serviço de Status.
     *
     * @param statusService service para manipular Status
     */
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    /**
     * Cria ou atualiza um registro de Status.
     *
     * @param status objeto Status no corpo da requisição
     * @return Status criado/atualizado
     */
    @PostMapping
    public ResponseEntity<Status> createOrUpdate(@RequestBody Status status) {
        Status saved = statusService.save(status);
        return ResponseEntity.ok(saved);
    }

    /**
     * Retorna todos os registros de Status.
     *
     * @return lista de Status
     */
    @GetMapping("/all")
    public ResponseEntity<List<Status>> getAll() {
        return ResponseEntity.ok(statusService.findAll());
    }

    /**
     * Retorna um Status específico pelo ID.
     *
     * @param id identificador do Status
     * @return Status encontrado, ou 404 se não existir
     */
    @GetMapping
    public ResponseEntity<Status> getById(@RequestParam(name = "id")  Integer id) {
        return statusService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Exclui um Status pelo ID.
     *
     * @param id identificador do Status
     * @return HTTP 204 em caso de sucesso
     */
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(name = "id") Integer id) {
        Optional<Status> optionalStatus = statusService.findById(id);
        if (optionalStatus.isPresent()) {
            statusService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
