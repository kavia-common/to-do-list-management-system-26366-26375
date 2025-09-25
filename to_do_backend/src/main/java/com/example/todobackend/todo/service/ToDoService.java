package com.example.todobackend.todo.service;

import com.example.todobackend.todo.dto.ToDoRequest;
import com.example.todobackend.todo.dto.ToDoResponse;
import com.example.todobackend.todo.model.ToDo;
import com.example.todobackend.todo.repository.ToDoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Service layer encapsulating ToDo business logic.
 */
@Service
@Transactional
public class ToDoService {

    private final ToDoRepository repository;

    public ToDoService(ToDoRepository repository) {
        this.repository = repository;
    }

    // PUBLIC_INTERFACE
    public ToDoResponse create(ToDoRequest request) {
        /** Create a new ToDo item from the given request. */
        ToDo e = new ToDo();
        e.setTitle(request.getTitle());
        e.setDescription(request.getDescription());
        e.setCompleted(Boolean.TRUE.equals(request.getCompleted()));
        e.setDueDate(request.getDueDate());
        ToDo saved = repository.save(e);
        return toResponse(saved);
    }

    // PUBLIC_INTERFACE
    public List<ToDoResponse> list(Boolean completed) {
        /** List ToDo items, optionally filtered by completion status. */
        List<ToDo> all = (completed == null) ? repository.findAll() : repository.findByCompleted(completed);
        return all.stream().map(this::toResponse).collect(Collectors.toList());
    }

    // PUBLIC_INTERFACE
    public ToDoResponse get(Long id) {
        /** Retrieve a single ToDo item by id. */
        ToDo e = repository.findById(id).orElseThrow(() -> new NoSuchElementException("ToDo not found: " + id));
        return toResponse(e);
    }

    // PUBLIC_INTERFACE
    public ToDoResponse update(Long id, ToDoRequest request) {
        /** Update a ToDo item by id using non-null fields from request. */
        ToDo e = repository.findById(id).orElseThrow(() -> new NoSuchElementException("ToDo not found: " + id));
        if (request.getTitle() != null) e.setTitle(request.getTitle());
        if (request.getDescription() != null) e.setDescription(request.getDescription());
        if (request.getCompleted() != null) e.setCompleted(request.getCompleted());
        if (request.getDueDate() != null || (request.getDueDate() == null && request.getDueDate() != e.getDueDate())) {
            e.setDueDate(request.getDueDate());
        }
        ToDo saved = repository.save(e);
        return toResponse(saved);
    }

    // PUBLIC_INTERFACE
    public void delete(Long id) {
        /** Delete a ToDo item by id. */
        if (!repository.existsById(id)) throw new NoSuchElementException("ToDo not found: " + id);
        repository.deleteById(id);
    }

    private ToDoResponse toResponse(ToDo e) {
        return new ToDoResponse()
                .setId(e.getId())
                .setTitle(e.getTitle())
                .setDescription(e.getDescription())
                .setCompleted(e.isCompleted())
                .setDueDate(e.getDueDate())
                .setCreatedAt(e.getCreatedAt())
                .setUpdatedAt(e.getUpdatedAt());
    }
}
