# User Authentication System

A secure user authentication system built with Spring Boot and Thymeleaf, featuring role-based access control for users and administrators.

## Features

- User Registration and Login
- Role-based Authorization (Admin/User)
- Secure Password Handling
- Admin Dashboard
  - User Management
  - Account Overview
  - Administrative Controls
- User Dashboard
  - Profile Management
  - Password Updates
  - Protected Content Access

## Technologies Used

- Spring Boot
- Spring Security
- Thymeleaf
- MySQL Database
- Tailwind CSS
- Font Awesome Icons

## Setup Instructions

1. Clone the repository:
```bash
git clone https://github.com/yourusername/user-authentication.git
```

2. Configure MySQL database in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

4. Access the application at `http://localhost:8484`

## Project Structure

```
user_authentication/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/authentication/
│   │   │       ├── controllers/
│   │   │       ├── models/
│   │   │       ├── repositories/
│   │   │       └── config/
│   │   └── resources/
│   │       ├── templates/
│   │       ├── static/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## Security Features

- Password Encryption using BCrypt
- Session Management
- CSRF Protection
- Role-based Access Control
- Secure Password Reset

## API Endpoints

- `/signup` - User Registration
- `/login` - User Login
- `/user/**` - User Protected Routes
- `/admin/**` - Admin Protected Routes

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

- Spring Boot Documentation
- Thymeleaf Documentation
- Tailwind CSS Documentation
