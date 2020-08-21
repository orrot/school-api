# Requirements

- Git
- Maven
- JDK 11
- IDE (Optional)

# How to tun the project?

- Just clone the repo as you prefer (ssh or https) 
- Compile the project using: `mvn clean install`
- Run the project using: `mvn spring-boot:run`
- OPT: You can run the main class in your favorite IDE to run the application.
- The port 8080 has to be available.

There is one endpoint that should work, once the application is running:
`localhost:8080/subject/list`

# Tips

- The main class of the project is com.school.api.SchoolApiApplication.
- You can insert new data using the script: `data-h2.sql`
- The Entities are not completed, it is required to build the relationships.
- The project already contains some useful libraries like lombok, spring-data and utilities libs.

# Examples
- The exercises to do are described in the controllers: `SubjectController` and `StudentController`
- Spring Data is already configured and a relational H2 DB (in memory) is already configured, so, nothing additional should be required.
