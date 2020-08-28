package com.school.api.service;

import java.util.List;

import com.school.api.model.dto.AverageNoteDto;
import com.school.api.model.dto.StudentDto;

public interface StudentService {

	List<AverageNoteDto> averageNotesByStudent();
	
	List<StudentDto> findStudentsByNamePrefix( String prefix );
    
}
