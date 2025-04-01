# RESTful API Project

A Spring Boot-based RESTful API project that provides endpoints for managing user data and authentication.

## Technologies Used

- Java 17
- Spring Boot 3.4.4
- Spring Security
- Spring Data JPA
- MySQL
- Maven

### Prerequisites

- JDK 17 or higher
- Maven 3.6 or higher
- MySQL Database

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
```

2. Configure the database connection in `src/main/resources/application.properties`

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

## API Endpoints

### Authentication
- `POST /auth/login` - User login
- `POST /auth/register` - User registration

### Users
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

## Security

The API uses JWT (JSON Web Tokens) for authentication. Include the JWT token in the Authorization header for protected endpoints:

```
Authorization: Bearer <your-token>
```

## Configuration

The application can be configured through the `application.properties` file:

- Server port
- Database connection
- JWT secret and expiration
- Logging levels

## Contributing

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
