package com.project.todo.services;

import com.project.todo.dominio.Tipo;
import com.project.todo.repositories.TipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe de serviço para manipular Tipo.
 */
@Service
public class TipoService {

    private final TipoRepository tipoRepository;

    /**
     * Construtor com injeção de dependência do repositório.
     *
     * @param tipoRepository repositório de Tipo
     */
    public TipoService(TipoRepository tipoRepository) {
        this.tipoRepository = tipoRepository;
    }

    /**
     * Cria ou atualiza um registro de Tipo.
     *
     * @param tipo objeto Tipo
     * @return Tipo salvo
     */
    public Tipo save(Tipo tipo) {
        return tipoRepository.save(tipo);
    }

    /**
     * Retorna todos os Tipos cadastrados.
     *
     * @return lista de Tipo
     */
    public List<Tipo> findAll() {
        return tipoRepository.findAll();
    }

    /**
     * Retorna um Tipo por ID.
     *
     * @param id identificador do tipo
     * @return Optional contendo o Tipo ou vazio
     */
    public Optional<Tipo> findById(Integer id) {
        return tipoRepository.findById(id);
    }

    /**
     * Exclui um Tipo pelo ID.
     *
     * @param id identificador do tipo
     */
    public void deleteById(Integer id) {
        tipoRepository.deleteById(id);
    }
}
