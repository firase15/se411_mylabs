# Copilot Instructions for `lab14`

## Project context
- This project is a Spring Boot Java application for course CRUD APIs.
- Java version is 21.
- Persistence uses Spring Data JDBC with H2 and SQL bootstrap scripts.
- Main package is `edu.psu.se411.lab14`.
- Use the project structure and code examples in `LAB14_REFERENCE.md` as the canonical reference.

## Required architecture and layering
- Keep the layered flow exactly as in the project examples:
  - `controller` -> HTTP/API concerns (`ResponseEntity`, routing, request/response handling, status codes)
  - `service` -> business logic and orchestration
  - `repository` -> Spring Data interfaces only
  - `model` -> immutable record-based data model with validation annotations
  - `exception` -> custom runtime exception classes
- Do not move business logic into controllers.
- Keep package names consistent with the project examples.

## Required Spring Boot implementation patterns
- Use constructor injection for service/repository dependencies.
- Follow the same REST controller pattern as the reference examples.
- Use `@RestController` and `@RequestMapping("/api/v1/courses")` for course endpoints unless a task explicitly requires a different path.
- Preserve the same endpoint behavior and status codes unless the task explicitly changes requirements.
- Keep controller methods small, explicit, and focused on HTTP concerns.

## Required CRUD patterns
- Reuse the same CRUD implementation pattern as the examples:
  - `GET /api/v1/courses` -> list all courses, return `200 OK`
  - `GET /api/v1/courses/{id}` -> return course or `404 Not Found`
  - `POST /api/v1/courses` -> create course, return `201 Created` with `Location` header
  - `PUT /api/v1/courses/{id}` -> update course, return `204 No Content` or `404 Not Found`
  - `DELETE /api/v1/courses/{id}` -> delete course, return `204 No Content` or `404 Not Found`
- Preserve the rule that a request body `id` must be `null` for create/update requests unless the task explicitly changes the requirement.
- Keep controller methods returning `ResponseEntity` with explicit status handling.

## Required service and repository patterns
- Service methods should mirror the reference project structure and naming:
  - list all records
  - fetch one record by id
  - create a record
  - update an existing record
  - delete an existing record
- Repository files should remain Spring Data interfaces, not custom implementations.
- Use standard repository methods such as `findAll()`, `findById()`, `save()`, and `deleteById()` as shown in the examples.
- Keep service logic straightforward and use `ObjectNotFoundException` for missing records.

## Required validation approach
- Use Jakarta Bean Validation annotations on model fields.
- Trigger validation with `@Valid` on request body parameters.
- Keep validation messages clear and user-facing.
- Follow the exact validation style from the project examples:
  - `code`: `@NotBlank`
  - `name`: `@NotBlank` and `@Size(min = 2)`
  - `credits`: `@NotNull`, `@Min(1)`, `@Max(10)`
- If a new model is created, place validation annotations directly on the fields/record components and keep the same conventions.

## Required exception handling
- Reuse `ObjectNotFoundException` for missing resource scenarios.
- Keep controller-level exception handling consistent with the examples when the task does not require a global handler.
- Use the same exception flow shown in the reference code: service checks existence and throws `ObjectNotFoundException`, controller catches it and returns `404 Not Found`.
- Do not introduce global handlers or unrelated exception infrastructure unless explicitly requested.

## Required database setup
- Keep the database bootstrap pattern used by the project examples.
- Use Spring Data JDBC with H2.
- Keep SQL setup in `schema.sql` and `data.sql` unless the task explicitly changes the database requirements.
- Preserve naming conventions such as `course`, `id`, `code`, `name`, `description`, and `credits` unless modified by the task.
- Do not change schema or seed data unless required by the assignment.

## Required code style
- Prefer the same code style and formatting used in the examples: clear, simple, direct Java code.
- Keep method names descriptive and aligned with the reference implementation.
- Use meaningful variable names and avoid unused imports, locals, or unnecessary abstractions.
- Do not refactor unrelated code or rewrite working logic just to improve style.
- Preserve the same package naming and import organization pattern used in the project.

## Required future work behavior
- When creating or updating Java files in future projects, follow the patterns in `LAB14_REFERENCE.md` exactly unless the task explicitly changes requirements.
- Adapt class names, package names, fields, and endpoints to new project requirements while preserving the same overall layered structure and coding conventions.
- Do not change working code unnecessarily.
- Use `LAB14_REFERENCE.md` as the main reference for Spring Boot CRUD structure, annotations, status codes, validation, repository methods, and exception handling.

## Do not do
- Do not add unrelated refactors or redesign the architecture.
- Do not change API paths/version without explicit request.
- Do not change SQL schema or seed data unless required.
- Do not introduce new frameworks or persistence approaches unless the task explicitly requires them.
- Do not modify working application code in a way that breaks the established CRUD behavior or validation conventions.