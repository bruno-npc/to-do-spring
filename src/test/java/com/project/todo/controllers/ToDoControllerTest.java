package com.project.todo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.todo.dominio.Status;
import com.project.todo.dominio.Tipo;
import com.project.todo.dominio.ToDo;
import com.project.todo.dto.TaskDTO;
import com.project.todo.services.ToDoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes de unidade (camada web) para o ToDoController,
 */
@WebMvcTest(ToDoController.class)
class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ToDoService toDoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /todo - deve criar ou atualizar uma tarefa")
    void testCreateOrUpdateToDo() throws Exception {
        ToDo requestToDo = new ToDo();
        requestToDo.setTitulo("Estudar Lombok");
        requestToDo.setDescricao("Verificar configuração");
        requestToDo.setDataCadastro(LocalDateTime.now());
        requestToDo.setStatus(new Status());
        requestToDo.getStatus().setId(1);
        requestToDo.setPrioridade(5);
        requestToDo.setTipo(new Tipo());
        requestToDo.getTipo().setId(2);

        ToDo savedToDo = new ToDo();
        savedToDo.setId(10L);
        savedToDo.setTitulo("Estudar Lombok");
        savedToDo.setDescricao("Verificar configuração");
        savedToDo.setDataCadastro(requestToDo.getDataCadastro());
        savedToDo.setDataAtualizacao(LocalDateTime.now());
        savedToDo.setStatus(requestToDo.getStatus());
        savedToDo.setPrioridade(5);
        savedToDo.setTipo(requestToDo.getTipo());

        Mockito.when(toDoService.save(any(ToDo.class))).thenReturn(savedToDo);

        mockMvc.perform(post("/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestToDo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.titulo").value("Estudar Lombok"))
                .andExpect(jsonPath("$.prioridade").value(5));

        Mockito.verify(toDoService).save(any(ToDo.class));
    }

    @Test
    @DisplayName("GET /todo - sem id retorna todas as tarefas")
    void testGetAllToDos() throws Exception {
        ToDo t1 = new ToDo();
        t1.setId(1L);
        t1.setTitulo("Tarefa 1");
        ToDo t2 = new ToDo();
        t2.setId(2L);
        t2.setTitulo("Tarefa 2");

        Mockito.when(toDoService.findAll()).thenReturn(Arrays.asList(t1, t2));

        mockMvc.perform(get("/todo/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].titulo").value("Tarefa 1"))
                .andExpect(jsonPath("$[1].titulo").value("Tarefa 2"));

        Mockito.verify(toDoService).findAll();
    }

    @Test
    @DisplayName("GET /todo?id=10 - deve retornar a tarefa com ID=10")
    void testGetOneToDo() throws Exception {
        ToDo t = new ToDo();
        t.setId(10L);
        t.setTitulo("Tarefa 10");

        Mockito.when(toDoService.findById(10L)).thenReturn(Optional.of(t));

        mockMvc.perform(get("/todo?id=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.titulo").value("Tarefa 10"));

        Mockito.verify(toDoService).findById(10L);
    }

    @Test
    @DisplayName("GET /todo?id=99 - se não existir retorna 404")
    void testGetOneToDoNotFound() throws Exception {
        Mockito.when(toDoService.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/todo?id=99"))
                .andExpect(status().isNotFound());

        Mockito.verify(toDoService).findById(99L);
    }

    @Test
    @DisplayName("DELETE /todo?id=10 - exclui a tarefa se existir")
    void testDeleteToDo() throws Exception {
        ToDo t = new ToDo();
        t.setId(10L);
        t.setTitulo("Tarefa 10");
        Mockito.when(toDoService.findById(10L)).thenReturn(Optional.of(t));

        mockMvc.perform(delete("/todo?id=10"))
                .andExpect(status().isNoContent());

        Mockito.verify(toDoService).deleteById(10L);
    }

    @Test
    @DisplayName("DELETE /todo?id=99 - retorna 404 se não encontrado")
    void testDeleteToDoNotFound() throws Exception {

        Mockito.when(toDoService.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/todo?id=99"))
                .andExpect(status().isNotFound());

        Mockito.verify(toDoService, Mockito.never()).deleteById(99L);
    }

    @Test
    @DisplayName("GET /todo/resume?id=99 - se não existir retorna 404")
    void testGetOneTaskDTO() throws Exception {

        Mockito.when(toDoService.findTaskByIdAsDTO(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/todo/resume?id=99"))
                .andExpect(status().isNotFound());

        Mockito.verify(toDoService).findTaskByIdAsDTO(99L);
    }

    @Test
    @DisplayName("GET /todo/allresume - lista vazia retorna 200 com lista vazia")
    void testGetAllTasksDTOEmpty() throws Exception {
        List<TaskDTO> emptyList = Collections.emptyList();

        Mockito.when(toDoService.findAllTasksAsDTO()).thenReturn(emptyList);

        mockMvc.perform(get("/todo/allresume"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0));

        Mockito.verify(toDoService).findAllTasksAsDTO();
    }

}
