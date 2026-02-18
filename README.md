# Spring Boot Subscription Manager

A lightweight service to track your monthly recurring expenses. This is a pet project built with modern tech, designed with scalability in mind.

## Concept
The idea is simple: keep all your subscriptions (Netflix, Spotify, Cloud services, etc.) in one place and instantly see how much you're spending per month.

## Key Features
- **Track Subscriptions**: Store provider name, amount, currency, and payment dates.
- **Calculate Costs**: Get the total monthly cost of all active subscriptions.
- **REST API**: Clean interface to manage your data.

## Tech Stack
- **Java 25**
- **Spring Boot 4.0.1**
- **PostgreSQL**
- **Docker**

## How to Run
1. **Start the Database**:
   ```bash
   docker-compose up -d
   ```
2. **Run the Application**:
   ```bash
   ./mvnw spring-boot:run
   ```

## Future Plans
This project is the foundation for a larger personal finance ecosystem. Potential additions include multi-currency support and email notifications.