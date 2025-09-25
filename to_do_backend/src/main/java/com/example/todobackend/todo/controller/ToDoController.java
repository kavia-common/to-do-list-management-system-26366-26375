package com.example.todobackend.todo.controller;

import com.example.todobackend.todo.dto.ToDoRequest;
import com.example.todobackend.todo.dto.ToDoResponse;
import com.example.todobackend.todo.service.ToDoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * REST controller for To-Do CRUD operations.
 * Endpoints follow RESTful conventions and include OpenAPI documentation.
 */
@RestController
@RequestMapping("/api/todos")
@Tag(name = "To-Do", description = "CRUD endpoints for managing to-do items")
public class ToDoController {

    private final ToDoService service;

    public ToDoController(ToDoService service) {
        this.service = service;
    }

    // PUBLIC_INTERFACE
    @PostMapping
    @Operation(
        summary = "Create To-Do",
        description = "Create a new to-do item.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Created",
                content = @Content(schema = @Schema(implementation = ToDoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
        }
    )
    public ResponseEntity<ToDoResponse> create(@Valid @RequestBody ToDoRequest request) {
        ToDoResponse created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(
        summary = "List To-Dos",
        description = "List all to-do items. Optionally filter by completion status using the 'completed' query parameter.",
        responses = @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = ToDoResponse.class)))
    )
    public ResponseEntity<List<ToDoResponse>> list(
            @Parameter(description = "Filter by completion status")
            @RequestParam(value = "completed", required = false) Boolean completed) {
        return ResponseEntity.ok(service.list(completed));
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(
        summary = "Get To-Do",
        description = "Retrieve a to-do item by its identifier.",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK",
                content = @Content(schema = @Schema(implementation = ToDoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
        }
    )
    public ResponseEntity<ToDoResponse> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.get(id));
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // PUBLIC_INTERFACE
    @PutMapping("/{id}")
    @Operation(
        summary = "Update To-Do",
        description = "Replace fields on a to-do item. Only non-null fields from the request will be applied.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Updated",
                content = @Content(schema = @Schema(implementation = ToDoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
        }
    )
    public ResponseEntity<ToDoResponse> update(@PathVariable Long id, @Valid @RequestBody ToDoRequest request) {
        try {
            return ResponseEntity.ok(service.update(id, request));
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete To-Do",
        description = "Delete a to-do item by id.",
        responses = {
            @ApiResponse(responseCode = "204", description = "Deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
        }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
