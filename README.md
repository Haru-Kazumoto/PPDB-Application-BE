# PPDB ONLINE APPLICATION

PPDB online adalah aplikasi untuk pendaftaran siswa masuk ke instansi sekolah secara online.

## Features

- 2 panel management (student and admin)
- User registration and login with JWT authentication
- Password encryption using BCrypt
- Role-based authorization with Spring Security
- Customized access denied handling
- Logout mechanism
- Refresh token

## Technologies

- Spring Boot 3.0
- Spring Security
- JSON Web Tokens (JWT)
- BCrypt
- Maven

## Getting Started

To get started with this project, you will need to have the following installed on your local machine:

- JDK 17+
- Maven 3+

## How To Deploy

- set working directory to frontend folder
- build frontend with command `npm run build`
- copy content of dist folder to frontend folder in backend folder
- run `mvn clean install` to build a jar
- copy jar to folder `/folder`
- run `docker build -t tiul/ppdb-yatindo:1.0.0 .` remember to change the version of 1.0.0 according to the latest version by `docker images`
- run `docker compose down`
- run `docker compose up -d`

## Running Seeder

### student

```shell
mvn clean spring-boot:run -Dspring-boot.run.arguments=--seeder=student
```

To build and run the project, follow these steps:

- Clone the repository: `git clone https://github.com/Haru-Kazumoto/PPDB-Application-BE.git`
- Navigate to the project directory: cd PPDB-Application-BE
- Add database "PPDB-DB" to postgres
- Build the project: mvn clean install
- Run the project: mvn spring-boot:run

-> The application will be available at http://localhost8080:.
