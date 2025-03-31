# BankFlow: Secure Transaction API

A Spring Boot-based RESTful API designed for a banking system, featuring secure user authentication, transaction management, and data integrity. Built to demonstrate proficiency in Java, Spring Boot, and modern backend development practices, tailored to fintech use cases.

## Features
- **User Management**: Register and login with JWT-based authentication.
- **Transaction Operations**: Deposit, withdraw, and transfer funds with balance validation and overdraft protection.
- **Data Integrity**: Atomic transactions using `@Transactional` and JPA/Hibernate for persistence.
- **Security**: Password hashing with BCrypt and secure API endpoints.

## Tech Stack
- **Java**: Core programming language.
- **Spring Boot**: Framework for building the REST API.
- **Spring Security**: JWT authentication and password encoding.
- **JPA/Hibernate**: Database management with MySQL (configurable).
- **Maven**: Dependency management and build tool.

## Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL (or your preferred database)
- Postman (for testing API endpoints)

## Setup and Installation
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/bankflow.git
   cd bankflow
2. **Configure Database**:
Update src/main/resources/application.properties with your database settings:
spring.datasource.url=jdbc:mysql://localhost:3306/bankflow_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

3. **Build and Run**:
mvn clean install
mvn spring-boot:run
The API will start on http://localhost:8080.

Usage
Test the API with Postman or any HTTP client:

Register a User:
POST /api/auth/register
Body: {"email": "user@example.com", "password": "pass123"}
Response: JWT token

Login:
POST /api/auth/login
Body: {"email": "user@example.com", "password": "pass123"}
Response: JWT token

Deposit Funds:
POST /api/accounts/{accountId}/deposit
Headers: Authorization: Bearer <jwt-token>
Body: 50.00
Withdraw Funds:

POST /api/accounts/{accountId}/withdraw
Headers: Authorization: Bearer <jwt-token>
Body: 30.00
Transfer Funds:

POST /api/accounts/transfer?fromAccountId=1&toAccountId=2&amount=10.00
Headers: Authorization: Bearer <jwt-token>

Project Highlights
Secure Authentication: Implemented JWT and BCrypt for user security.
Transaction Logic: Built atomic operations with balance checks using @Transactional.
Learning Journey: Developed as a self-directed project to master Spring Boot and fintech concepts.
Future Enhancements
Add unit tests with JUnit and Mockito.
Deploy to a cloud platform (e.g., Heroku, AWS).
Enhance input validation and error handling.
Contributing
Feel free to fork this repo and submit pull requests with improvements!

Author
Yiğit Özay

LinkedIn: [(https://www.linkedin.com/in/yi%C4%9Fit-%C3%B6zay/?trk=opento_sprofile_details](https://www.linkedin.com/in/yi%C4%9Fit-%C3%B6zay/)
Email: yigit.ozay344@gmail.com
