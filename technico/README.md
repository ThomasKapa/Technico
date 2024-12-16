# Technico Backend Project

## Description

The **Technico Backend Project** is a backend application for a renovation contractor agency. It facilitates managing property repairs and customer data, providing distinct functionalities for administrators and property owners. The backend ensures secure data handling, efficient processing of operations, and role-based access control.

---

## Functional Requirements

### User Roles

- **Admin (Agency Employee)**: Manages property owners, properties, and repairs.
- **Property Owner**: Views and manages repairs related to their properties.

### Key Features

1. **Authentication & Authorization**:
   - Role-based access (Admin, Property Owner).
   - Secure login with email and password.
2. **Property Owner Management**:
   - Create, update, delete, and search property owner details.
   - Fields include VAT number, name, surname, address, phone, email, username, password.
3. **Property Management**:
   - Create, update, delete, and search properties.
   - Fields include property ID, address, year of construction, VAT number of owner.
4. **Repair Management**:
   - Create, update, delete, and search repairs.
   - Fields include repair date, status (pending, in progress, complete), type, cost, description, owner, and repair address.
5. **Dashboard Views**:
   - Admin: Displays upcoming and ongoing repairs.
   - Property Owner: Displays repairs related to their properties.

---

## Technologies Used

- **Programming Language**: Java 21
- **Framework**: Spring Boot
- **Database**: MySQL Server
- **Version Control**: GitHub for source control

---


## Installation

### Prerequisites

- Java JDK 21
- Maven
- MySQL Server

### Setup

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd Technico
   ```
2. Configure the database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/technico
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   ```
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

---

## API Endpoints

### Authentication

| Endpoint      | HTTP Method | Description         |
| ------------- | ----------- | ------------------- |
| `/auth/login` | POST        | User authentication |

### Property Owners

| Endpoint       | HTTP Method | Description               |
| -------------- | ----------- | ------------------------- |
| `/owners`      | GET/POST    | Create or retrieve owners |
| `/owners/{id}` | PUT/DELETE  | Update or delete an owner |
| `/owners/search` | GET       | Search for owners based on VAT or email |

### Properties

| Endpoint           | HTTP Method | Description                   |
| ------------------ | ----------- | ----------------------------- |
| `/properties`      | GET/POST    | Create or retrieve properties |
| `/properties/{id}` | PUT/DELETE  | Update or delete a property   |
| `/properties/search` | GET       | Search for properties based on Property ID or VAT |

### Repairs

| Endpoint          | HTTP Method | Description                              |
| ----------------- | ----------- | ---------------------------------------- |
| `/repairs`        | GET/POST    | Create or retrieve repairs               |
| `/repairs/{id}`   | PUT/DELETE  | Update or delete a repair                |
| `/repairs/search` | GET         | Search for repairs by date or owner ID   |

---

## Testing

Run tests using the following command:

```bash
mvn test
```

---

## Contribution

1. Fork the repository.
2. Create a new branch for your feature:
   ```bash
   git checkout -b feature/your-feature
   ```
3. Commit your changes with a descriptive message:
   ```bash
   git commit -m "Add new feature"
   ```
4. Push to the branch:
   ```bash
   git push origin feature/your-feature
   ```
5. Open a pull request.

---



