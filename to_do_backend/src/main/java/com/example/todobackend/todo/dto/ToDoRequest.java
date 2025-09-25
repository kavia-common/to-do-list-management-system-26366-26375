package com.example.todobackend.todo.dto;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request body for creating or updating a ToDo item.
 */
public class ToDoRequest {

    @NotBlank
    @Size(max = 140)
    private String title;

    @Size(max = 2000)
    private String description;

    private Boolean completed;

    private Instant dueDate;

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Boolean getCompleted() { return completed; }

    public void setCompleted(Boolean completed) { this.completed = completed; }

    public Instant getDueDate() { return dueDate; }

    public void setDueDate(Instant dueDate) { this.dueDate = dueDate; }
}
