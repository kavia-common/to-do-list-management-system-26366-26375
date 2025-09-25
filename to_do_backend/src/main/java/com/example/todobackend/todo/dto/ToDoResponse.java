package com.example.todobackend.todo.dto;

import java.time.Instant;

/**
 * Response body for ToDo item data.
 */
public class ToDoResponse {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private Instant dueDate;
    private Instant createdAt;
    private Instant updatedAt;

    public Long getId() { return id; }

    public ToDoResponse setId(Long id) { this.id = id; return this; }

    public String getTitle() { return title; }

    public ToDoResponse setTitle(String title) { this.title = title; return this; }

    public String getDescription() { return description; }

    public ToDoResponse setDescription(String description) { this.description = description; return this; }

    public boolean isCompleted() { return completed; }

    public ToDoResponse setCompleted(boolean completed) { this.completed = completed; return this; }

    public Instant getDueDate() { return dueDate; }

    public ToDoResponse setDueDate(Instant dueDate) { this.dueDate = dueDate; return this; }

    public Instant getCreatedAt() { return createdAt; }

    public ToDoResponse setCreatedAt(Instant createdAt) { this.createdAt = createdAt; return this; }

    public Instant getUpdatedAt() { return updatedAt; }

    public ToDoResponse setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; return this; }
}
