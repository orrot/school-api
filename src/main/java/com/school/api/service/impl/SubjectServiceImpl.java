package com.school.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.api.model.dto.BestNoteDto;
import com.school.api.model.dto.Subject;
import com.school.api.model.entities.EnrolledSubjectEntity;
import com.school.api.model.entities.StudentEntity;
import com.school.api.model.entities.SubjectEntity;
import com.school.api.repository.StudentRepository;
import com.school.api.repository.SubjectRepository;
import com.school.api.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Subject> list() {
        List<SubjectEntity> subjectEntities = subjectRepository.findAll();
        return subjectEntities.stream()
                .map( SubjectEntity::toDTO )
                .collect( Collectors.toList() );
    }

	// This will not be returning any results for subjects without students
	@Override
	public List<BestNoteDto> getBestNoteBySubject() 
	{
        List<SubjectEntity> subjectEntities = subjectRepository.findAll();
        List<BestNoteDto> dtos = subjectEntities.stream()
        		.map( this::findBestNoteForSubject )
        		.filter( Optional::isPresent )
        		.map( Optional::get )
        		.collect( Collectors.toList() );
        return dtos;
	}

	private Optional<BestNoteDto> findBestNoteForSubject( SubjectEntity subject ) 
	{
		Optional<EnrolledSubjectEntity> bestStudentKey = subject.getStudentsTakingSubject().stream()
				.max( this::compareEnrolledSubjects );
		
		if ( !bestStudentKey.isPresent() ) {
			return Optional.empty();
		}
		
		StudentEntity student = bestStudentKey.flatMap( key -> 
			studentRepository.findById( key.getStudentId() ) ).get();
		
		return Optional.of( BestNoteDto.builder()
				.studentName( student.getName() )
				.bestNote( bestStudentKey.get().getNote() )
				.subjectName( subject.getName() )
				.build() );
	}
	
	// If two students have equal notes, the "smallest" ID is deemed lower.
	private int compareEnrolledSubjects( EnrolledSubjectEntity e1, EnrolledSubjectEntity e2 )
	{
		if ( e1.getNote() < e2.getNote() ) { 
			return -1;
		}
		else if ( e1.getNote() > e2.getNote() ) {
			return 1;
		}
		else {
			return e1.getStudentId().compareTo( e2.getStudentId() );
		}
	}

	public void setSubjectRepository( SubjectRepository subjectRepository ) 
	{
		this.subjectRepository = subjectRepository;
	}

	public void setStudentRepository( StudentRepository studentRepository ) 
	{
		this.studentRepository = studentRepository;
	}
	
}
