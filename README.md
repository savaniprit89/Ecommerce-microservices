# E-Commerce Microservices Platform

This project is a backend e-commerce system built using a microservices architecture.
The goal was to design a scalable and loosely coupled platform where different business domains such as product management, order processing, inventory validation, and payment handling operate independently while communicating through asynchronous events.

The system demonstrates event-driven design using Apache Kafka and performance optimization using Redis caching.

## Architecture

The application is split into multiple Spring Boot services:

* **API Gateway** – central entry point and request routing
* **Product Service** – manages product catalog with Redis caching
* **Order Service** – handles order creation and orchestration
* **Inventory Service** – validates and reserves stock
* **Payment Service** – processes payment events (simulated)
* **Notification Service** – listens to events and logs system activity
* **Common Module** – shared Kafka event models

Services communicate using Kafka topics, allowing asynchronous processing and better scalability.

## Order Processing Flow

1. Order is created through API Gateway
2. Order Service publishes an event to Kafka
3. Inventory Service validates stock availability
4. Payment Service processes payment after stock reservation
5. Notification Service logs order lifecycle events

This flow demonstrates event-driven orchestration across services.

## Tech Stack

* Java 17
* Spring Boot
* PostgreSQL
* Redis
* Apache Kafka
* Spring Cloud Gateway
* Spring Data JPA
* Maven

## Running Locally

### Prerequisites

* PostgreSQL running on localhost
* Redis running on localhost
* Kafka running on localhost
* Java 17
* Maven

Create databases:

* productdb
* orderdb
* inventorydb

### Start services

Run each service in a separate terminal:

```
mvn -pl product-service spring-boot:run
mvn -pl order-service spring-boot:run
mvn -pl inventory-service spring-boot:run
mvn -pl payment-service spring-boot:run
mvn -pl notification-service spring-boot:run
mvn -pl api-gateway spring-boot:run
```
