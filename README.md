# E-commerce Microservices - Setup and Deployment

This repository contains a multi-module e-commerce application built with Spring Boot microservices, designed to run in a Dockerized environment. This guide walks through the steps to set up, build, and run all services using Docker and Docker Compose.

---

## 📑 Table of Contents

1. [Prerequisites](#1-prerequisites)
2. [Setup Workflow](#2-setup-workflow)
   - [Clone the Repository](#clone-the-repository)
   - [Create Environment Variables File (.env)](#create-environment-variables-file-env)
   - [Optional: Cleanup Previous Container Setup](#optional-cleanup-previous-container-setup)
   - [Run the Setup and Deployment Script](#run-the-setup-and-deployment-script)
   - [Terminate All Services](#terminate-all-services)
3. [Local Database Setup Details](#3-local-database-setup-details)
   - [Persistent Data](#persistent-data)
   - [Tables Creation](#tables-creation)
4. [Application Environments](#4-application-environments)
5. [Consuming Endpoints with Postman](#5-consuming-endpoints-with-postman)
6. [Monitoring and Tracing](#6-monitoring-and-tracing)

---

## 1. Prerequisites

Ensure the following tools are installed:

- **Git**: For cloning the repository
- **Docker Desktop / Docker Engine & Compose**
- **Apache Maven 3.x**
- **JDK 21**: Set `JAVA_HOME` accordingly
- **Postman** or similar API client

---

## 2. Setup Workflow

### Clone the Repository

```bash
git clone https://github.com/BrianVega/multi-module--e-commerce.git
cd multi-module--e-commerce
```

### Create Environment Variables File (.env)

Create a `.env` file in the root of the project:

```bash
touch .env
```

Then add:

```env
# Eureka URI
EUREKA_URI=http://spring-cloud-config-server:8761/eureka

# PostgreSQL DB
DB_URL=jdbc:postgresql://database:5432/<your_database_name>
DB_USER=<your_database_user>
DB_PASSWORD=<your_database_password>
DB_NAME=<your_database_name>
DB_PORT=5432

# Currency Converter API
CURRENCY_CONVERTER_API_KEY=<Generated API Key>
CURRENCY_CONVERTER_URL=http://data.fixer.io/api/latest

# JWT Secret
JWT_SECRET=<Your_Strong_JWT_Secret_Here>
```

> **Note**: `database` is the hostname used inside Docker Compose network.

### Optional: Cleanup Previous Container Setup

```bash
docker-compose down -v
```

Removes containers, volumes, and networks for a clean start.

### Run the Setup and Deployment Script

```bash
./run.sh
```

This script will:

- Authenticate to Docker Hub if necessary
- Build each microservice with Maven
- Generate or use existing Dockerfiles
- Create multi-platform images (amd64, arm64)
- Push images to Docker Hub
- Deploy all services using `docker-compose up -Vd`

### Terminate All Services

Stop and remove all running services:

```bash
docker-compose down
```

With volume removal:

```bash
docker-compose down -v
```

---

## 3. Local Database Setup Details

PostgreSQL container defined in `docker-compose.yml`:

```yaml
database:
  image: postgres:latest
  container_name: orders-db
  environment:
    POSTGRES_USER: ${DB_USER}
    POSTGRES_PASSWORD: ${DB_PASSWORD}
    POSTGRES_DB: ${DB_NAME}
  ports:
    - "${DB_PORT}:5432"
  volumes:
    - db_data:/var/lib/postgresql/data
    - ./e-commerce/db:/docker-entrypoint-initdb.d
  networks:
    - ecommerce-private-network
```

### Persistent Data

- Volume `db_data` persists PostgreSQL data.

### Tables Creation

- SQL scripts in `./e-commerce/db/init_db.sql` are automatically executed on first container init if data is empty.

---

## 4. Application Environments

Spring profiles are used for config separation:

### `application.properties`

```properties
spring.profiles.active=dev
```

### `application-local.properties`

For local development with H2:

```properties
gd.datasource.url=jdbc:h2:mem:localdb
gd.datasource.username=sa
gd.datasource.password=password
gd.datasource.driverClassName=org.h2.Driver
gd.jpa.hibernate.ddl-auto=create
```

### `application-dev.properties`

Connects to Docker-managed PostgreSQL:

```properties
gd.datasource.url=${DB_URL}
gd.datasource.username=${DB_USER}
gd.datasource.password=${DB_PASSWORD}
```

---

## 5. Consuming Endpoints with Postman

Access microservices via API Gateway on `http://localhost:8765`.

Example:

### Login Request

```http
POST http://localhost:8765/auth/login
```

**Body (JSON)**:
```json
{
  "username": "manager",
  "password": "password"
}
```

### Get Payment Details

```http
GET http://localhost:8765/payment-details/get/1
```

> Make sure to use `http://` (not `https://`) to avoid SSL issues.

### Postman Workspace Setup

#### Import Workspace JSON

1. Open Postman
2. Click "Workspaces" → "Import"
3. Upload `GD-P_e-commerce-api.postman_collection.json`

#### Import Environment JSON

1. Go to "Environments" in sidebar
2. Click "Import" → Upload `GD-P_e-commerce-env.postman_environment.json`

#### Usage
Use endpoints under Gateway directory, or their correspondent endpoints based on root directory

---

## 6. Monitoring and Tracing

The stack includes observability tools:

| Tool       | URL                        | Purpose                              |
|------------|----------------------------|--------------------------------------|
| **Zipkin** | http://localhost:9411      | Distributed tracing                  |
| **Prometheus** | http://localhost:9090  | Metrics collection & querying        |
| **Grafana** | http://localhost:3000     | Dashboard for Prometheus data        |

> Grafana default credentials: `admin` / `admin`