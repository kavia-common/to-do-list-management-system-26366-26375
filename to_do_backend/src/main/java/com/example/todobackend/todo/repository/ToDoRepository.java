package com.example.todobackend.todo.repository;

import com.example.todobackend.todo.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA repository for ToDo entities.
 */
@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    List<ToDo> findByCompleted(boolean completed);
}
