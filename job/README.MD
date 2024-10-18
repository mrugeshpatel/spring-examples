# Project Name

This project is a Craft application**Spring Boot 3.3.4**, **Spring Data JPA**, **Lombok**,**Spring Actuator**,**Model Mapper (V3.1.1)**, **H2** in-memory database, **Swagger Open-API (v2.0.3) for API documentation, and is containerized with **Docker**.

## Table of Contents

- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
    - [Run the Application](#run-the-application)
    - [Access H2 Console](#access-h2-console)
    - [Access Swagger UI](#access-swagger-ui)
- [Building with Docker](#building-with-docker)
- [Testing](#testing)
- [License](#license)

## Project Overview

This project is a demonstration of using Spring Boot 3 with Spring Data JPA for interacting with a database, H2 as an in-memory database for quick development and testing, and Swagger Open-API for auto-generating API documentation. The project is also Dockerized for easy deployment.

## Technologies Used

- **Spring Boot 3**: Main framework used for building the application.
- **Spring Data JPA**: Provides easy integration with relational databases.
- **H2 Database**: In-memory database used for development and testing purposes.
- **Lombok**: Boilerplate code generation,
- **Spring Actuator**: Application monitoring
- **Model Mapper (V3.1.1)**: Object mapping
- **Swagger Open-API UI(v2.0.3)**: Tool for documenting and testing REST APIs.
- **Docker**: Used for containerizing the application.

## Features

- **CRUD operations** with Spring Data JPA.
- **H2** database console for testing and query execution.
- **Swagger UI** for interactive API documentation.
- **Docker** support for containerizing the application.

## Prerequisites

- **Java 21** or higher
- **Maven 3.8+**
- **Docker** (if you want to run the application in a container)

## Getting Started

### Run the Application

You can run the application using the following command:

```bash
./mvnw clean package
java -jar target/app.jar
```

### Access H2 Console

```bash
http://localhost:8080/h2-console
```
- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: (leave it empty)

### Access Swagger UI
```bash
http://localhost:8080/swagger-ui/index.html

```
You can view and test all the available APIs through this interface.

### REST API Endpoints
Here are the REST API endpoints exposed by the application:

#### 1. Create a Job
- Method: POST
- Endpoint: /api/jobs
- Description: Create a new job posting.
- Request Body:
```bash
{
  "title": "Software Engineer",
  "description": "Development role",
  "rate": 120.0,
  "postedOn": "2024-10-14",
  "status": "ACTIVE"
}

```





### Building with Docker
1. Build the Docker image:
```bash
docker build -t spring-boot-app .

```
2. Run the Docker container:
```bash
docker-compose up --build

```

3. Stopping the Docker containers:
```bash
docker-compose down
  
```

### Testing
```bash
./mvnw test
  
```

### License
This project is licensed under the MIT License.

```

