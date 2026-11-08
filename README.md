# Spring Boot Member Management — Midterm Project

A Java Spring Boot web application developed as a university project. It practices a basic member-management workflow using Spring MVC, Thymeleaf, a service layer, and JDBC access to MySQL.

## Technology stack

- Java 21
- Spring Boot 3.5.6
- Spring Web / Spring MVC
- Thymeleaf
- MySQL Connector/J
- Gradle

## Application structure

```text
src/main/java/com/example/_5/
├── DTO/          # MemberDTO
├── controller/  # Member web routes
├── service/     # Member application logic
└── Repository/  # JDBC database access
src/main/resources/
├── templates/   # Thymeleaf views
└── application.properties
```

## Run locally

### Requirements

- JDK 21
- MySQL
- A local database named `webserver` (as configured in application properties)

Configure your local MySQL username and password without committing credentials.

```bash
git clone https://github.com/Manishr767/midterm.git
cd midterm
./gradlew bootRun
```

The application is configured to use port **8082**. Open [http://localhost:8082](http://localhost:8082).

## Learning objectives

- Build server-rendered pages with Thymeleaf
- Handle web requests with Spring MVC controllers
- Separate controller, service, and repository layers
- Perform database operations through JDBC

## Development note

This is an academic practice project. Password storage, authorization, validation, and destructive actions require additional security work before the application could be considered suitable for real users.

## Author

**Manish Rauniyar** · [GitHub](https://github.com/Manishr767)
