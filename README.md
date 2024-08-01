# Vehicle Management System

## Overview

The Vehicle Management System, built with Java Spring Boot, allows users to register, authenticate, and manage vehicles
with role-based access control.

## Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- MySQL database

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/your-username/vehicle-management-system.git
    cd vehicle-management-system
    ```

2. Configure the database:
   Update the `application.properties` file in the `src/main/resources` directory with your database configuration.

3. Install the dependencies:
    ```sh
    mvn clean install
    ```


## API Documentation

http://localhost:8080/swagger-ui/index.html

1. Login or Register to Obtain Token:
2. Authorize in Swagger UI
3. Click on the Authorize button, enter your JWT token then click Authorize button
4. After authorizing, you can make authenticated API requests directly from the Swagger UI.

