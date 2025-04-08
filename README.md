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

## Endpoints

### Books (`/api/libro`)
- `GET /` - List all books
- `GET /activos` - List only active books 
- `GET /{isbn}` - Get book by ISBN
- `POST /crear` - Create new book
- `GET /editorial/{editorialId}` - Get books by publisher
- `GET /autor/{autorId}` - Get books by author
- `GET /search` - Search books by author and/or publisher
  - Query params: 
    - autorId (optional)
    - editorialId (optional)
- `GET /search/{txt}` - Search books (title, author, publisher) by txt
- `DELETE /eliminar/{isbn}` - Hard delete book
    
### Authors (`/api/autor`)
- `GET /` - List all authors
- `GET /activos` - List active authors
- `GET /{id}` - Get author by ID
- `POST /crear` - Create author
- `PATCH /actualizar` - Update author
- `PATCH /actualizarDTO` - Update author with DTO
- `PATCH /eliminar` - Soft delete author
- `DELETE /eliminar/{id}` - Hard delete author
- `GET /search/{txt}` - Search author by txt

### Publishers (`/api/editorial`)
- `GET /` - List all publishers
- `GET /activas` - List active publishers
- `GET /{id}` - Get publisher by ID
- `POST /crear` - Create publisher
- `POST /crearDTO` - Create publisher with DTO
- `PATCH /actualizar` - Update publisher
- `PATCH /eliminar` - Soft delete publisher
- `DELETE /eliminar/{id}` - Hard delete publisher
- `GET /search/{txt}` - Search publisher by txt

<!-- ### Authentication
- `POST /auth/login` - User login
- `POST /auth/register` - User registration -->

<!-- ### Users
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user -->

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
