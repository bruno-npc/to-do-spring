package com.project.todo.services;

import com.project.todo.dominio.Status;
import com.project.todo.repositories.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe de serviço para manipular Status.
 */
@Service
public class StatusService {

    private final StatusRepository statusRepository;

    /**
     * Construtor com injeção de dependência do repositório.
     *
     * @param statusRepository repositório de Status
     */
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    /**
     * Cria ou atualiza um registro de status.
     *
     * @param status objeto Status
     * @return Status salvo
     */
    public Status save(Status status) {
        return statusRepository.save(status);
    }

    /**
     * Retorna todos os Status cadastrados.
     *
     * @return lista de Status
     */
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    /**
     * Retorna um Status por ID.
     *
     * @param id identificador do status
     * @return Optional contendo o Status ou vazio
     */
    public Optional<Status> findById(Integer id) {
        return statusRepository.findById(id);
    }

    /**
     * Exclui um Status pelo ID.
     *
     * @param id identificador do status
     */
    public void deleteById(Integer id) {
        statusRepository.deleteById(id);
    }
}
