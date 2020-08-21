# Requirements

- Git
- Maven
- JDK 11 (I think it could be tested with JDK 8, you can validate the pom java source properties and maybe the javassist library is not required in v8)
- IDE (Optional) Please, consider that the project uses Lombok, so you need to configure your IDE with lombok (In IntelliJ is just to install the plugin).

# How to run the project?

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

# Description of the examples (REST endpoints to do)

- Exercise 1: return the average note for each student
    We need to know the id, the name, the grade of the student and of course the average note of all the subjects ordered from best to worse note
    Example:
    - Student 1 - Math - 4.5
    - Student 1 - Biology - 4.7
    - Student 2 - Math - 2.3

    The user need to see as JSON:
    - Student 1 (all the info) - 4.6
    - Student 2 (all the info) - 2.3

    NOTE: Please solve the problem using business logic at application level (Java Code).

- Exercise 2: return the list of students where the name begins from a variable String (the string will be sent from the UI)
    NOTE: Please solve the problem retrieving the result directly FROM the database.
    
- Exercise 3:
    Retrieve the student with the best note for every subject. It does not matter the grade.
    It is only required the name of the subject.
    It is expected to return the results (as JSON):
    - Math - Orlando - 4.6
    - Biology - Manuel - 4.9
    ...
    
# Description of the Project

The project uses package by layer (not the best approach, however, this is a very small project). You will find:`controllers`, `services`, `repositories`, `dtos` and `entities`. There is an example in the SubjectController that uses all the layers, however, for the other examples you might need to add code in all the layers (and even add new classes).

The important thing to understand is that we have 3 Entities: Student, Subject and EnrolledSubject. EnrolledSubject is the entity that is building the relationship between Student and Subject, however, it is not a weak entity, because the note is included in that, to store the note of the Student in that Subject. As you can see, it is not considering the grade or other important things, however, the objetive is not to build a great application, but to do some basic examples.


Subject --* EnrolledSubject *-- Student





