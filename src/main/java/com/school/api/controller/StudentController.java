package com.school.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class StudentController {

    // TODO return the average note for each student
    // We need to know the id, the name, the grade of the student and of course the average note of all the subjects ordered from best to worse note
    // Example:
    // Student 1 - Math - 4.5
    // Student 1 - Biology - 4.7
    // Student 2 - Math - 2.3

    // The user need to see as JSON:
    // Student 1 (all the info) - 4.6
    // Student 2 (all the info) - 2.3

    // NOTE: Please solve the problem using business logic at application level (Java Code).

    // TODO return the list of students where the name begins from a variable String (the string will be sent from the UI)
    // NOTE: Please solve the problem retrieving the result directly FROM the database.
}
