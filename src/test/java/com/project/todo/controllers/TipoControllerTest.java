package com.project.todo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.todo.dominio.Tipo;
import com.project.todo.services.TipoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes de unidade para TipoController,
 * considerando endpoints todo/tipos
 */
@WebMvcTest(TipoController.class)
class TipoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TipoService tipoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /todo/tipos - cria ou atualiza um tipo")
    void testCreateOrUpdateTipo() throws Exception {
        Tipo request = new Tipo();
        request.setId(null);
        request.setTipo("Rotina");

        Tipo saved = new Tipo();
        saved.setId(10);
        saved.setTipo("Rotina");

        Mockito.when(tipoService.save(any(Tipo.class))).thenReturn(saved);

        mockMvc.perform(post("/todo/tipos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.tipo").value("Rotina"));
    }

    @Test
    @DisplayName("GET /todo/tipos - sem id retorna todos os tipos")
    void testGetAllTipos() throws Exception {
        Tipo t1 = new Tipo();
        t1.setId(1);
        t1.setTipo("Projeto");
        Tipo t2 = new Tipo();
        t2.setId(2);
        t2.setTipo("Urgente");

        Mockito.when(tipoService.findAll()).thenReturn(Arrays.asList(t1, t2));

        mockMvc.perform(get("/todo/tipos/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].tipo").value("Projeto"))
                .andExpect(jsonPath("$[1].tipo").value("Urgente"));
    }

    @Test
    @DisplayName("GET /todo/tipos?id=1 - retorna tipo id=1")
    void testGetOneTipo() throws Exception {
        Tipo tipo = new Tipo();
        tipo.setId(1);
        tipo.setTipo("Projeto");

        Mockito.when(tipoService.findById(1)).thenReturn(Optional.of(tipo));

        mockMvc.perform(get("/todo/tipos?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tipo").value("Projeto"));
    }

    @Test
    @DisplayName("GET /todo/tipos?id=99 - 404 se não encontrado")
    void testGetOneTipoNotFound() throws Exception {
        Mockito.when(tipoService.findById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/todo/tipos?id=99"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /todo/tipos?id=2 - exclui tipo se existir")
    void testDeleteTipo() throws Exception {
        Tipo tipo = new Tipo();
        tipo.setId(2);
        tipo.setTipo("Rotina");
        Mockito.when(tipoService.findById(2)).thenReturn(Optional.of(tipo));

        mockMvc.perform(delete("/todo/tipos?id=2"))
                .andExpect(status().isNoContent());

        Mockito.verify(tipoService).deleteById(2);
    }

    @Test
    @DisplayName("DELETE /todo/tipos?id=10 - 404 se não encontrado")
    void testDeleteTipoNotFound() throws Exception {
        Mockito.when(tipoService.findById(10)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/todo/tipos?id=10"))
                .andExpect(status().isNotFound());

        Mockito.verify(tipoService, Mockito.never()).deleteById(10);
    }
}
