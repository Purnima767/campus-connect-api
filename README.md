# CampusConnect — Student & Placement Management API

A portfolio-grade Java 17 + Spring Boot REST API built to demonstrate layered backend design, validation, pagination, filtering, persistence, and API documentation.

## Highlights
- Student CRUD with validation
- Pagination and sorting
- Search by name, email, or department
- Department and graduation-year filters
- Global error responses
- DTOs instead of exposing entities
- OpenAPI/Swagger API docs
- Unit tests for service logic

## Architecture
- **Controller:** HTTP routes and request/response handling.
- **Service:** business rules and transaction boundaries.
- **Repository:** Spring Data JPA database access.
- **Entity:** persisted domain model.
- **Request/response DTOs:** separate external API shape from database entities.
- **Global exception handler:** consistent JSON errors.

## Tech stack
Java 17 · Spring Boot · Spring Web · Spring Data JPA · Bean Validation · H2 · Maven · JUnit 5 · Mockito · springdoc OpenAPI

## Run locally
1. Install JDK 17+ and Maven.
2. Open this folder in VS Code.
3. In the terminal, run `mvn spring-boot:run`.
4. Open Swagger UI: `http://localhost:8080/swagger-ui.html`.
5. H2 console: `http://localhost:8080/h2-console` with JDBC URL `jdbc:h2:file:./data/campus-connect-api`, user `sa`, blank password.

## API endpoints
| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/v1/students?page=0&size=10` | Paginated list |
| GET | `/api/v1/students?query=purnima` | Search name/email |
| GET | `/api/v1/students?department=CSE` | Filter department |
| GET | `/api/v1/students/{id}` | Get one student |
| POST | `/api/v1/students` | Create |
| PUT | `/api/v1/students/{id}` | Update |
| DELETE | `/api/v1/students/{id}` | Delete (204) |

## Example request
`sample-request.json` has a sample JSON body. With the server running, create a record:

```bash
curl -X POST http://localhost:8080/api/v1/students -H "Content-Type: application/json" -d @sample-request.json
```

## Run tests
```bash
mvn test
```

## Push to GitHub
Create a new empty repository named `campus-connect-api` and run these commands from this folder:

```bash
git init
git add .
git commit -m "Build CampusConnect — Student & Placement Management API"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/campus-connect-api.git
git push -u origin main
```
Replace `YOUR-USERNAME` with your GitHub username. Do not commit credentials or private data.

## Interview preparation
Practice explaining the request lifecycle: HTTP request → controller → validated DTO → service/business logic → repository/JPA → database → response DTO. Explain why pagination matters, why entities are not returned directly, how validation and error handling improve API usability, and what you would add next.

## Roadmap for future versions
- Switch H2 to MySQL/PostgreSQL with environment-based configuration.
- Add Spring Security, JWT authentication, and roles.
- Add Docker and CI checks with GitHub Actions.
- Add integration tests and Testcontainers.
- Build a React frontend and deploy the API.
