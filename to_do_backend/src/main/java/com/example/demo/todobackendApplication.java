package com.example.todobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

/**
 * Main entry point for the To-Do Backend application.
 * Configures OpenAPI metadata and starts the Spring Boot context.
 */
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "To-Do Backend API - Ocean Professional",
        version = "0.1.0",
        description = "RESTful API for managing to-do items. Modern design with Ocean Professional theme (blue & amber accents).",
        contact = @Contact(name = "To-Do API Support", email = "support@example.com"),
        license = @License(name = "Apache 2.0")
    ),
    externalDocs = @ExternalDocumentation(
        description = "Project Home",
        url = "https://example.com/todo"
    )
)
public class todobackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(todobackendApplication.class, args);
    }
}
