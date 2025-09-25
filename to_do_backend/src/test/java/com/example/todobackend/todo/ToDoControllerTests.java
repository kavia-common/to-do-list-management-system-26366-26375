package com.example.todobackend.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Minimal smoke tests for the ToDoController using MockMvc.
 */
@SpringBootTest
@AutoConfigureMockMvc
class ToDoControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void createListDeleteFlow() throws Exception {
        // Create
        String payload = mapper.writeValueAsString(Map.of(
                "title", "Test Task",
                "description", "Smoke test item"
        ));
        String created = mockMvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andReturn().getResponse().getContentAsString();

        long id = mapper.readTree(created).get("id").asLong();

        // List
        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").exists());

        // Delete
        mockMvc.perform(delete("/api/todos/{id}", id))
                .andExpect(status().isNoContent());
    }
}
