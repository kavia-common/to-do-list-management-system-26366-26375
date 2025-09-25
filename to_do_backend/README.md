# To-Do Backend (Spring Boot) — Ocean Professional

A clean, modern REST API for managing to-do items with CRUD operations. 
Styled with the Ocean Professional theme (primary #2563EB, secondary #F59E0B).

Quick start:
- Run: ./gradlew bootRun
- Docs: http://localhost:8080/swagger-ui.html
- Health: http://localhost:8080/health
- H2 Console: http://localhost:8080/h2-console (JDBC: jdbc:h2:mem:tododb, user: sa, password: empty)

API endpoints:
- POST /api/todos — Create a to-do
- GET /api/todos — List to-dos (optional ?completed=true|false)
- GET /api/todos/{id} — Get by id
- PUT /api/todos/{id} — Update by id (non-null fields are applied)
- DELETE /api/todos/{id} — Delete by id

Environment:
- Uses in-memory H2 by default. If a to_do_database is configured, supply Spring datasource properties via environment variables in the container runtime; do not hardcode credentials.

Ocean Professional palette:
- primary: #2563EB (blue)
- secondary/success: #F59E0B (amber)
- error: #EF4444
- background: #f9fafb
- surface: #ffffff
- text: #111827

License: Apache-2.0
