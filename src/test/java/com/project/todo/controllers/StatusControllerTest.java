package com.project.todo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.todo.dominio.Status;
import com.project.todo.services.StatusService;
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
 * Testes de unidade para StatusController,
 * considerando endpoints /todo/status
 */
@WebMvcTest(StatusController.class)
class StatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StatusService statusService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /todo/status - cria ou atualiza um status")
    void testCreateOrUpdateStatus() throws Exception {
        Status request = new Status();
        request.setId(null);
        request.setStatus("Pendente");

        Status saved = new Status();
        saved.setId(1);
        saved.setStatus("Pendente");

        Mockito.when(statusService.save(any(Status.class))).thenReturn(saved);

        mockMvc.perform(post("/todo/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("Pendente"));
    }

    @Test
    @DisplayName("GET /todo/status - sem id retorna todos os status")
    void testGetAllStatus() throws Exception {
        Status s1 = new Status();
        s1.setId(1);
        s1.setStatus("Pendente");
        Status s2 = new Status();
        s2.setId(2);
        s2.setStatus("Concluído");

        Mockito.when(statusService.findAll()).thenReturn(Arrays.asList(s1, s2));

        mockMvc.perform(get("/todo/status/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].status").value("Pendente"))
                .andExpect(jsonPath("$[1].status").value("Concluído"));
    }

    @Test
    @DisplayName("GET /todo/status?id=2 - retorna o status com id=2")
    void testGetOneStatus() throws Exception {
        Status s = new Status();
        s.setId(2);
        s.setStatus("Concluído");

        Mockito.when(statusService.findById(2)).thenReturn(Optional.of(s));

        mockMvc.perform(get("/todo/status?id=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.status").value("Concluído"));
    }

    @Test
    @DisplayName("GET /todo/status?id=99 - 404 se não encontrado")
    void testGetOneStatusNotFound() throws Exception {
        Mockito.when(statusService.findById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/todo/status?id=99"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /todo/status?id=1 - exclui se existir")
    void testDeleteStatus() throws Exception {
        Status s = new Status();
        s.setId(1);
        s.setStatus("Pendente");

        Mockito.when(statusService.findById(1)).thenReturn(Optional.of(s));

        mockMvc.perform(delete("/todo/status?id=1"))
                .andExpect(status().isNoContent());

        Mockito.verify(statusService).deleteById(1);
    }

    @Test
    @DisplayName("DELETE /todo/status?id=50 - 404 se não encontrado")
    void testDeleteStatusNotFound() throws Exception {
        Mockito.when(statusService.findById(50)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/todo/status?id=50"))
                .andExpect(status().isNotFound());

        Mockito.verify(statusService, Mockito.never()).deleteById(50);
    }
}
