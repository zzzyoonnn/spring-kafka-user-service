# Spring Kafka User Service

A Spring Boot microservice that handles user registration and publishes `UserCreated` events to Apache Kafka.

This service demonstrates how business events can be published asynchronously to enable loose coupling between microservices.

---

## Features

- Register users
- Persist user information in H2 Database
- Publish `UserCreated` events to Kafka
- Asynchronous event publishing

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- H2 Database
- Apache Kafka
- Gradle

---

## Architecture

```
Client
   │
   ▼
User Service
   │
   ▼
Persist User
   │
   ▼
Publish UserCreated Event
   │
   ▼
Kafka Topic (user-created)
```

---

## Event Flow

1. Client sends a signup request.
2. User information is persisted in the H2 database.
3. The User Service publishes a `UserCreated` event to Kafka.
4. Downstream services consume the event asynchronously.

---

## Purpose

The User Service is responsible only for user registration and event publishing.

Rather than directly invoking downstream services through REST APIs, it publishes a `UserCreated` event to Kafka. This allows other services to process the event independently, improving service decoupling and fault isolation.

---

## Related Project

- [spring-kafka-practice](https://github.com/zzzyoonnn/spring-kafka-practice)
- [spring-kafka-email-service](https://github.com/zzzyoonnn/spring-kafka-email-service)