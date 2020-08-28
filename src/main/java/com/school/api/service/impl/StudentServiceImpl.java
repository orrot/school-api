package com.school.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.api.model.dto.AverageNoteDto;
import com.school.api.model.dto.StudentDto;
import com.school.api.model.entities.EnrolledSubjectEntity;
import com.school.api.model.entities.StudentEntity;
import com.school.api.repository.StudentRepository;
import com.school.api.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Again, no returning average for students without subjects
	@Override
	public List<AverageNoteDto> averageNotesByStudent() {
		return studentRepository.findAll().stream()
				.map( this::buildAverageForStudent )
				.filter( Optional::isPresent )
				.map( Optional::get )
				.collect( Collectors.toList() );
	}

	@Override
	public List<StudentDto> findStudentsByNamePrefix( String prefix ) 
	{
		if ( prefix == null || prefix.isEmpty() ) {
			return new ArrayList<>();
		}
		List<StudentEntity> students = studentRepository.findByNamePrefix( prefix ); //studentRepository.findByNameStartingWith( prefix );
		return students.stream()
				.map( x -> new StudentDto( x.getId(), x.getName(), x.getGrade() ) )
				.collect( Collectors.toList() );
	}

	private Optional<AverageNoteDto> buildAverageForStudent( StudentEntity student )
	{
		if ( student.getSubjectsTaken().isEmpty() ) {
			return Optional.empty();
		}
		Double avg = student.getSubjectsTaken().stream()
				.map( EnrolledSubjectEntity::getNote )
				.reduce( (a, b) -> a + b )
				.get() / student.getSubjectsTaken().size();
		return Optional.of( AverageNoteDto.builder()
				.studentId( student.getId() )
				.studentName( student.getName() )
				.studentGrade( student.getGrade() )
				.average( avg )
				.build() );
	}
	
	public void setStudentRepository( StudentRepository studentRepository )
	{
		this.studentRepository = studentRepository;
	}

}
