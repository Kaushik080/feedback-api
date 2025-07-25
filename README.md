# Spring Boot Feedback API
A REST API for managing feedback with validation and comprehensive testing built with Spring Boot 3.5.3 and PostgreSQL.

## Features
Submit feedback with validation (name ≥ 3 chars, valid email, message ≥ 10 chars)

Retrieve all feedback entries

Unit tests with Mockito and Jakarta Bean Validation

PostgreSQL database integration

Automatic timestamp generation

## Technologies Used
Spring Boot 3.5.3

Java 17

PostgreSQL

JUnit 5 & Mockito

Lombok

Jakarta Validation

Spring Data JPA

## Prerequisites
Java 17 or higher installed

PostgreSQL running on your system

Maven 3.6+ installed (or use the included Maven wrapper)

Database Setup

## Database Setup
### Create PostgreSQL Database:

CREATE DATABASE feedbackdb;

### Configure Database Connection:
Update src/main/resources/application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/feedbackdb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

## How to Run the Project
### Using Maven
mvn spring-boot:run

### Using Maven Wrapper
./mvnw spring-boot:run

The application will start on http://localhost:8080/feedback

## Sample API Requests

using Insomnia

valid request
{
  "name": "Bob Smith",
  "email": "bob.smith@company.com",
  "message": "The application works perfectly!"
}

Invalid requests

{
  "name": "Bo",
  "email": "bob.smith@company.com",
  "message": "Name is too short"
},

{
  "name": "Bob Smith",
  "email": "bob.smithcompany.com",
  "message": "The email id is invalid"
},

{
  "name": "Bob Smith",
  "email": "bob.smith@company.com",
  "message": "short"
}

## How to Run Tests

### using Maven
mvn test

### using Maven wrapper
./mvnw test
