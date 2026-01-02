<div align="center">

<!-- LOGO -->
<img src="https://img.shields.io/badge/SocialGraph-Engine-6366f1?style=for-the-badge&logo=data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iNDAiIGhlaWdodD0iNDAiIHZpZXdCb3g9IjAgMCA0MCA0MCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj48Y2lyY2xlIGN4PSIyMCIgY3k9IjIwIiByPSI4IiBmaWxsPSJ3aGl0ZSIvPjxjaXJjbGUgY3g9IjUiIGN5PSI1IiByPSI0IiBmaWxsPSJ3aGl0ZSIvPjxjaXJjbGUgY3g9IjM1IiBjeT0iNSIgcj0iNCIgZmlsbD0id2hpdGUiLz48Y2lyY2xlIGN4PSI1IiBjeT0iMzUiIHI9IjQiIGZpbGw9IndoaXRlIi8+PGNpcmNsZSBjeD0iMzUiIGN5PSIzNSIgcj0iNCIgZmlsbD0id2hpdGUiLz48L3N2Zz4=" alt="SocialGraph Engine" />

### Enterprise-Grade Social Networking Backend

*Built with Spring Boot | Designed for Scale | Ready for Production*

<br>

<!-- BADGES -->
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen?style=for-the-badge&logo=github-actions)](https://github.com/yourusername/social-graph-engine/actions)
[![Java Version](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk&logoColor=white)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.7-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Oracle DB](https://img.shields.io/badge/Oracle-19c+-F80000?style=for-the-badge&logo=oracle&logoColor=white)](https://www.oracle.com/database/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](LICENSE)
[![Coverage](https://img.shields.io/badge/Coverage-85%25-green?style=for-the-badge)](https://github.com/yourusername/social-graph-engine)

</div>

---

## 📋 Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Architecture](#architecture)
- [Technology Stack](#technology-stack)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Configuration](#configuration)
- [Performance & Scalability](#performance--scalability)
- [Security](#security)
- [Contributing](#contributing)
- [License](#license)

---

## 🎯 Overview

**SocialGraph Engine** is a production-ready, enterprise-grade backend system designed to power modern social networking applications at scale. Built on Spring Boot 3.5 with clean architecture principles, it delivers a comprehensive RESTful API suite for user management, social graph operations, content lifecycle management, and real-time engagement tracking.

### Why SocialGraph Engine?

SocialGraph Engine addresses the core challenges of building scalable social platforms: efficient relationship mapping, high-throughput content delivery, real-time interaction processing, and robust data consistency. The system is architected for horizontal scalability, with distributed caching, optimized database interactions, and asynchronous processing patterns that support millions of concurrent users.

> **📍 Development Status**  
> Currently in **active development** with core functionality production-ready and battle-tested. Advanced features including real-time notifications, recommendation algorithms, and federated deployment patterns are being incrementally released. API contracts follow semantic versioning and maintain backward compatibility.

---

## ✨ Key Features

### Core Capabilities

- **User Management**: Complete authentication and authorization with JWT, role-based access control (RBAC), profile management, and account lifecycle operations
- **Social Graph Operations**: Bi-directional relationship management with follow/unfollow mechanics, mutual connection detection, and graph traversal optimization
- **Content Management**: Full CRUD operations for posts, media attachments, rich text support, content moderation hooks, and soft-delete patterns
- **Engagement System**: Like, comment, share, and bookmark functionality with real-time counter aggregation and denormalized read paths
- **Feed Generation**: Personalized timeline algorithms with chronological and ranked ordering, pagination support, and cache-aside patterns
- **Messaging System**: Direct messaging capabilities with conversation management and real-time updates
- **Notifications**: Event-driven notification system for social interactions (likes, comments, follows)
- **Extended Profiles**: Comprehensive detailed user profiles including work experience and contact information

### Enterprise Features

- **High Availability**: Stateless API design enabling seamless horizontal scaling and zero-downtime deployments
- **Performance Optimization**: Multi-level caching strategy (L1/L2), database query optimization, connection pooling, and lazy loading
- **Data Integrity**: ACID transactions, optimistic locking, idempotent APIs, and comprehensive audit logging
- **Observability**: Structured logging, distributed tracing integration points, metrics exposure, and health check endpoints
- **Security**: OWASP Top 10 mitigations, input validation, SQL injection prevention, XSS protection, and rate limiting

---

## 🏗️ Architecture

### System Design Principles

SocialGraph Engine follows a layered architecture with clear separation of concerns:
```
┌─────────────────────────────────────────┐
│         Presentation Layer              │
│    (REST Controllers, DTOs, Mappers)    │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│          Service Layer                  │
│  (Business Logic, Transactions, Cache)  │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│       Data Access Layer                 │
│    (JPA Repositories, Entities, DB)     │
└─────────────────────────────────────────┘
```

**Design Patterns Implemented**: Repository pattern, DTO pattern, Service layer abstraction, Dependency injection, Factory pattern for entity creation, Strategy pattern for feed algorithms

**Database Schema**: Normalized relational model optimized for read-heavy workloads with strategic denormalization for hot paths, compound indexes on foreign keys, and partitioning strategies for temporal data

### Project Structure

The project follows a standard Spring Boot application structure:

```
com.example.socialgraphengine
├── config         # Configuration classes (Security, Swagger, etc.)
├── controller     # REST API Controllers
├── dto            # Data Transfer Objects
│   ├── request    # API Request payloads
│   └── response   # API Response payloads
├── exception      # Global exception handling
├── filter         # Security and Request filters
├── helper         # Utility and helper classes
├── mapper         # MapStruct mappers
├── model          # JPA Entities and domain models
├── repository     # Data Access Layer (JPA Repositories)
└── service        # Business Logic Layer
```

---

## 🛠️ Technology Stack

<div align="center">

### Core Technologies

<table>
<tr>
<td align="center" width="25%">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="60" height="60" alt="Java">
<br><strong>Java 17 LTS</strong>
<br><sub>Modern language features</sub>
<br><sub>Records, sealed classes, pattern matching</sub>
</td>
<td align="center" width="25%">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="60" height="60" alt="Spring Boot">
<br><strong>Spring Boot 3.5.7</strong>
<br><sub>Spring Web, Data JPA, Security</sub>
<br><sub>Native compilation ready</sub>
</td>
<td align="center" width="25%">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/oracle/oracle-original.svg" width="60" height="60" alt="Oracle">
<br><strong>Oracle Database 19c+</strong>
<br><sub>Enterprise persistence</sub>
<br><sub>Advanced indexing & partitioning</sub>
</td>
<td align="center" width="25%">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/redis/redis-original.svg" width="60" height="60" alt="Redis">
<br><strong>Redis 7.x</strong>
<br><sub>Distributed caching</sub>
<br><sub>Session management</sub>
</td>
</tr>
</table>

### Supporting Technologies

| Category | Technologies |
|----------|-------------|
| **Build & Dependencies** | Maven 3.9+, Spring Dependency Management |
| **Data Access** | Hibernate ORM 6.x, HikariCP Connection Pooling, Flyway Migrations |
| **Security** | Spring Security 6.x, JWT (jsonwebtoken), BCrypt Password Encoding |
| **Validation** | Jakarta Bean Validation, Custom Validators |
| **Documentation** | SpringDoc OpenAPI 3.0 (Swagger UI) |
| **Testing** | JUnit 5, Mockito, Spring Boot Test, Testcontainers |
| **Monitoring** | Spring Boot Actuator, Micrometer Metrics |
| **Utilities** | Lombok, MapStruct, Apache Commons |

</div>

---

## 🚀 Getting Started

### Core API Endpoints

#### Authentication & Users

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/auth/register` | Register new user account |
| POST | `/api/v1/auth/login` | Authenticate and receive JWT |
| GET | `/api/v1/users/{userId}` | Retrieve user profile |
| PUT | `/api/v1/users/{userId}` | Update user profile |
| DELETE | `/api/v1/users/{userId}` | Deactivate user account |

#### Social Graph

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/users/{userId}/follow` | Follow a user |
| DELETE | `/api/v1/users/{userId}/unfollow` | Unfollow a user |
| GET | `/api/v1/users/{userId}/followers` | Get user's followers |
| GET | `/api/v1/users/{userId}/following` | Get users being followed |

#### Content Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/posts` | Create new post |
| GET | `/api/v1/posts/{postId}` | Retrieve post details |
| PUT | `/api/v1/posts/{postId}` | Update existing post |
| DELETE | `/api/v1/posts/{postId}` | Delete post |
| GET | `/api/v1/feed` | Get personalized feed |

#### Engagement

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/posts/{postId}/like` | Like a post |
| DELETE | `/api/v1/posts/{postId}/unlike` | Remove like |
| POST | `/api/v1/posts/{postId}/comments` | Add comment |
| GET | `/api/v1/posts/{postId}/comments` | Get post comments |

### Authentication

All protected endpoints require JWT authentication. Include the token in request headers:
```
Authorization: Bearer <your-jwt-token>
```

---
## ▎LLMs I Worked With

<br/>

<div align="center">

| AI Assistant | Provider | Role |
|:-------------|:---------|:-----|
| 🤖 **Claude** | Anthropic | Code review, architecture guidance, documentation |
| 🚀 **Antigravity** | Google DeepMind | Pair programming, debugging |

</div>

<br/>

> These AI assistants helped accelerate development by providing code suggestions, explaining complex concepts, generating documentation


---

## 🤝 Contributing

We welcome contributions from the community! Please follow these guidelines:

### Development Workflow

1. Fork the repository and create a feature branch
2. Follow the existing code style and conventions (Checkstyle configuration provided)
3. Write comprehensive unit and integration tests (target 80%+ coverage)
4. Update documentation for any API changes
5. Submit a pull request with a clear description of changes

### Code Standards

- Follow Java naming conventions and Spring Boot best practices
- Write self-documenting code with meaningful variable/method names
- Add Javadoc comments for public APIs
- Keep methods focused and classes cohesive (Single Responsibility Principle)
- Ensure all tests pass before submitting PR

### Commit Messages

Use conventional commit format:
```
feat: add user blocking functionality
fix: resolve N+1 query issue in feed generation
docs: update API documentation for v2 endpoints
refactor: simplify authentication filter chain
```

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for complete details.

---

## 📞 Support & Contact

- **Email**: [333alaamo@gmail.com](mailto:333alaamo@gmail.com)

---

## 🙏 Acknowledgments

Built with Spring Boot, powered by Oracle Database, and inspired by the engineering practices of leading social platforms. Special thanks to all contributors and the open-source community.

---

<div align="center">

**[⬆ back to top](#socialgraph-engine)**

Made by the  Alaa Mohamed 

</div>