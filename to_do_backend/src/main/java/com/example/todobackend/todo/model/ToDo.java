package com.example.todobackend.todo.model;

import java.time.Instant;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * To-Do entity representing a single task item with status and metadata.
 */
@Entity
@Table(name = "todos", indexes = {
        @Index(name = "idx_todos_completed", columnList = "completed"),
        @Index(name = "idx_todos_dueDate", columnList = "dueDate")
})
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 140)
    @Column(nullable = false, length = 140)
    private String title;

    @Size(max = 2000)
    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private boolean completed = false;

    @Column
    private Instant dueDate;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private Instant updatedAt = Instant.now();

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }

    // Getters and Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }

    public void setCompleted(boolean completed) { this.completed = completed; }

    public Instant getDueDate() { return dueDate; }

    public void setDueDate(Instant dueDate) { this.dueDate = dueDate; }

    public Instant getCreatedAt() { return createdAt; }

    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
