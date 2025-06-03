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
- Spring Data JPA
- Thymeleaf
- MySQL Database
- Tailwind CSS
- Font Awesome Icons

## Setup Instructions

1. Clone the repository:
```bash
git clone [https://github.com/yourusername/user-authentication.git](https://github.com/ParamShukla007/PRODIGY_FS_01.git)
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
- Role-based Access Control
- Secure Password Reset

## API Endpoints

- `/signup` - User Registration
- `/login` - User Login
- `/user/**` - User Protected Routes
- `/admin/**` - Admin Protected Routes

## Acknowledgments

- Spring Boot Documentation
- Thymeleaf Documentation
- Tailwind CSS Documentation
