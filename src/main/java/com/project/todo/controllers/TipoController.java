package com.project.todo.controllers;

import com.project.todo.dominio.Tipo;
import com.project.todo.services.TipoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para a entidade Tipo.
 */
@RestController
@RequestMapping("/todo/tipos")
public class TipoController {

    private final TipoService tipoService;

    /**
     * Construtor com injeção de dependência do serviço de Tipo.
     *
     * @param tipoService service para manipular Tipo
     */
    public TipoController(TipoService tipoService) {
        this.tipoService = tipoService;
    }

    /**
     * Cria ou atualiza um registro de Tipo.
     *
     * @param tipo objeto Tipo no corpo da requisição
     * @return Tipo criado/atualizado
     */
    @PostMapping
    public ResponseEntity<Tipo> createOrUpdate(@RequestBody Tipo tipo) {
        Tipo saved = tipoService.save(tipo);
        return ResponseEntity.ok(saved);
    }

    /**
     * Retorna todos os registros de Tipo.
     *
     * @return lista de Tipo
     */
    @GetMapping("/all")
    public ResponseEntity<List<Tipo>> getAll() {
        return ResponseEntity.ok(tipoService.findAll());
    }

    /**
     * Retorna um Tipo específico pelo ID.
     *
     * @param id identificador do Tipo
     * @return Tipo encontrado, ou 404 se não existir
     */
    @GetMapping
    public ResponseEntity<Tipo> getById(@RequestParam(name = "id") Integer id) {
        return tipoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Exclui um Tipo pelo ID.
     *
     * @param id identificador do Tipo
     * @return HTTP 204 em caso de sucesso
     */
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(name = "id") Integer id) {
        tipoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
