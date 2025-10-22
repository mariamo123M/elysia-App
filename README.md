Elysia Music App – Final Project
Technologies

Java 17

Spring Boot

Spring Security + JWT

JavaMailSender

PostgreSQL

JPA / Hibernate

Swagger

JUnit + Mockito (Coverage ≥ 80%)
How to Run

Clone the repo:

git clone https://github.com/mariamo123M/elysiaa.git


Configure application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/elysia
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=youremail@gmail.com
spring.mail.password=yourpassword
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true


Run SpotifyApplication.java

Access Swagger UI: http://localhost:8080/swagger-ui.html
Roles

Admin – manually added in DB

Listener – registers & verifies via email

Artist – registers, chooses ARTIST role
Authentication

JWT-based login

Use Authorize button in Swagger to test protected endpoints
Email Verification

After registration, user receives 6-digit code

Verify via:

POST /auth/verify
Body:
{
"email": "user@example.com",
"code": "123456"
}

Endpoints
Auth

POST /auth/register → register user

POST /auth/login → login & get JWT

POST /auth/verify → verify email

Playlists

GET /playlists → get my playlists

POST /playlists?name=... → create playlist

PUT /playlists/{id}?name=... → update playlist

DELETE /playlists/{id} → delete playlist

Music

GET /music/{id} → play music (increments playCount)

GET /music/search?keyword=... → search music

Artist

POST /artist/albums?name=...&genre=... → create album

POST /artist/music?albumId=...&name=...&genre=...&author=... → upload music

GET /artist/profile/{id}/similar → similar artists

Recommendations

GET /recommendations/playlists → suggested playlists by top genres

Statistics

Music playCount increments every /music/{id} call

Weekly stats printed every Friday (scheduled task)

Testing

Run:

mvn test
Coverage ≥ 80% via Jacoco

Git Flow

Branches: main, develop, feature/*, release/*

Tip:
For recommended playlists, system calculates top 3 genres based on playCount per user.
For similar artists, it finds other artists with the same genre excluding the current artist.
