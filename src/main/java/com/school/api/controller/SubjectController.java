package com.school.api.controller;

import com.school.api.model.dto.BestNoteDto;
import com.school.api.model.dto.Subject;
import com.school.api.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("list")
    // EXAMPLE CALL: http://localhost:8080/subject/list
    public List<Subject> list() {
        return subjectService.list();
    }

    // Retrieve the student with the best note for every subject. It does not matter the grade.
    // It is only required the name of the subject.
    // It is expected to return the results (as JSON):
    // Math - Orlando - 4.6
    // Biology - Manuel - 4.9
    // ...
    @GetMapping( "maxnotes" )
    public List<BestNoteDto> getBestNoteBySubject() 
    {
    	return subjectService.getBestNoteBySubject();
    }

}
