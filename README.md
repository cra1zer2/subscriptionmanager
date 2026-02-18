# Spring Boot Subscription Manager

A lightweight service to track your monthly recurring expenses. This is a pet project built with modern tech, designed with scalability in mind.

## Concept
The idea is simple: keep all your subscriptions (Netflix, Spotify, Cloud services, etc.) in one place and instantly see how much you're spending per month.

## Key Features
- **Track Subscriptions**: Store provider name, amount, currency, and payment dates.
- **Multi-currency Support**: Automatically converts different currencies (USD, EUR, etc.) to PLN using the NBP API.
- **Calculate Costs**: Get the total monthly cost of all active subscriptions in a single currency.
- **OpenAPI Documentation**: Interactive REST API documentation with Swagger UI.

## Tech Stack
- **Java 25**
- **Spring Boot 4.0.1**
- **PostgreSQL**
- **Docker & Testcontainers**

## How to Run
1. **Start the Database**:
   ```bash
   docker-compose up -d
   ```
2. **Run the Application**:
   ```bash
   ./mvnw spring-boot:run
   ```
3. **Explore the API**:
   Access the Swagger UI at `http://localhost:8080/swagger-ui/index.html`

## Future Plans
This project is the foundation for a larger personal finance ecosystem. Potential additions include:
- Email notifications for upcoming payments.
- Dashboard UI for better visualization.
- User authentication and multi-user support.