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

<!-- ### Authentication
- `POST /auth/login` - User login
- `POST /auth/register` - User registration -->

<!-- ### Users
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user -->

### Books
- `GET /api/books` - Get all books
- `GET /api/books/{id}` - Get book by ID
- `POST /api/books` - Add a new book
- `PUT /api/books/{id}` - Update book details
- `DELETE /api/books/{id}` - Delete a book

<!-- ### Categories
- `GET /api/categories` - Get all categories
- `GET /api/categories/{id}` - Get category by ID
- `POST /api/categories` - Add a new category
- `PUT /api/categories/{id}` - Update category details
- `DELETE /api/categories/{id}` - Delete a category -->

<!-- ### Orders
- `GET /api/orders` - Get all orders
- `GET /api/orders/{id}` - Get order by ID
- `POST /api/orders` - Create a new order
- `PUT /api/orders/{id}` - Update order details
- `DELETE /api/orders/{id}` - Delete an order -->

### Editorials
- `GET /api/editorial` - Get all editorials
- `POST /api/editorial/crear` - Create a new editorial
- `PATCH /api/editorial/actualizar` - Update an editorial
- `PATCH /api/editorial/eliminar` - Delete an editorial
- `GET /api/editorial/activas` - Get active/inactive editorials

### Authors
- `GET /api/autor` - Get all authors
- `POST /api/autor/crear` - Create a new author
- `PATCH /api/autor/actualizar` - Update an author
- `PATCH /api/autor/eliminar` - Delete an author
- `GET /api/autor/activos` - Get active/inactive authors

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
