package com.school.api.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;

import com.school.api.model.dto.BestNoteDto;
import com.school.api.model.entities.EnrolledSubjectEntity;
import com.school.api.model.entities.StudentEntity;
import com.school.api.model.entities.StudentEntity.Grade;
import com.school.api.model.entities.SubjectEntity;
import com.school.api.repository.StudentRepository;
import com.school.api.repository.SubjectRepository;

public class SubjectServiceImplTest {

	@Test
	public void testShouldReturnBestNotesFor3ValidSubjects() 
	{
		StudentRepository mockStudentRepo = Mockito.mock( StudentRepository.class );
		StudentEntity entity = new StudentEntity( 
				"1", "Nombre Uno", Grade.FOUR, new ArrayList<>() );
		StudentEntity entity2 = new StudentEntity( 
				"2", "Nombre Dos", Grade.THREE, new ArrayList<>() );
		StudentEntity entity3 = new StudentEntity( 
				"3", "Nombre Tres", Grade.FIVE, new ArrayList<>() );
		List<StudentEntity> entities = Arrays.asList( entity, entity2, entity3 );
		Mockito.when( mockStudentRepo.findById( "1" ) ).thenReturn( Optional.of( entities.get( 0 ) ) );
		Mockito.when( mockStudentRepo.findById( "2" ) ).thenReturn( Optional.of( entities.get( 1 ) ) );
		Mockito.when( mockStudentRepo.findById( "3" ) ).thenReturn( Optional.of( entities.get( 2 ) ) );
		
		SubjectRepository mockSubjectRepo = Mockito.mock( SubjectRepository.class );
		SubjectEntity subject = new SubjectEntity( "1", "Subject 1", 
				Arrays.asList( 
						new EnrolledSubjectEntity( "1", "1", 4D ), 
						new EnrolledSubjectEntity( "1", "2", 3.8D ), 
						new EnrolledSubjectEntity( "1", "3", 4.5D ) ) );
		SubjectEntity subject2 = new SubjectEntity( "2", "Subject 2", 
				Arrays.asList( 
						new EnrolledSubjectEntity( "2", "1", 4.5D ), 
						new EnrolledSubjectEntity( "2", "2", 3.8D ), 
						new EnrolledSubjectEntity( "2", "3", 3.6D ) ) );
		SubjectEntity subject3 = new SubjectEntity( "3", "Subject 3", 
				Arrays.asList( 
						new EnrolledSubjectEntity( "3", "1", 4.6D ), 
						new EnrolledSubjectEntity( "3", "3", 4.5D ) ) );
		List<SubjectEntity> subjects = Arrays.asList( subject, subject2, subject3 );
		Mockito.when( mockSubjectRepo.findAll() ).thenReturn( subjects );
		
		SubjectServiceImpl service = new SubjectServiceImpl();
		service.setStudentRepository( mockStudentRepo );
		service.setSubjectRepository( mockSubjectRepo );
		List<BestNoteDto> dtos = service.getBestNoteBySubject();
		assertEquals( 3, dtos.size() );
		for ( int i = 0; i < 3 ;i++ ) {
			assertEquals( subjects.get(i).getName(), dtos.get(i).getSubjectName() );
		}
		assertEquals( 4.5D, dtos.get(0).getBestNote(), 0.001D );
		assertEquals( 4.5D, dtos.get(1).getBestNote(), 0.001D );
		assertEquals( 4.6D, dtos.get(2).getBestNote(), 0.001D );
	}
	
	@Test
	public void testShouldBreakTieByStudentId()
	{
		StudentRepository mockStudentRepo = Mockito.mock( StudentRepository.class );
		StudentEntity entity = new StudentEntity( 
				"1", "Nombre Uno", Grade.FOUR, new ArrayList<>() );
		StudentEntity entity2 = new StudentEntity( 
				"2", "Nombre Dos", Grade.THREE, new ArrayList<>() );
		StudentEntity entity3 = new StudentEntity( 
				"3", "Nombre Tres", Grade.FIVE, new ArrayList<>() );
		List<StudentEntity> entities = Arrays.asList( entity, entity2, entity3 );
		Mockito.when( mockStudentRepo.findById( "1" ) ).thenReturn( Optional.of( entities.get( 0 ) ) );
		Mockito.when( mockStudentRepo.findById( "2" ) ).thenReturn( Optional.of( entities.get( 1 ) ) );
		Mockito.when( mockStudentRepo.findById( "3" ) ).thenReturn( Optional.of( entities.get( 2 ) ) );
		
		SubjectRepository mockSubjectRepo = Mockito.mock( SubjectRepository.class );
		SubjectEntity subject = new SubjectEntity( "1", "Subject 1", 
				Arrays.asList( 
						new EnrolledSubjectEntity( "1", "1", 4.5D ), 
						new EnrolledSubjectEntity( "1", "2", 3.8D ), 
						new EnrolledSubjectEntity( "1", "3", 4.5D ) ) );
		SubjectEntity subject2 = new SubjectEntity( "2", "Subject 2", 
				Arrays.asList( 
						new EnrolledSubjectEntity( "2", "1", 4.4D ), 
						new EnrolledSubjectEntity( "2", "2", 3.8D ), 
						new EnrolledSubjectEntity( "2", "3", 4.5D ) ) );
		List<SubjectEntity> subjects = Arrays.asList( subject, subject2 );
		Mockito.when( mockSubjectRepo.findAll() ).thenReturn( subjects );
		
		SubjectServiceImpl service = new SubjectServiceImpl();
		service.setStudentRepository( mockStudentRepo );
		service.setSubjectRepository( mockSubjectRepo );
		List<BestNoteDto> dtos = service.getBestNoteBySubject();
		assertEquals( 2, dtos.size() );
		for ( int i = 0; i < 2 ;i++ ) {
			assertEquals( subjects.get(i).getName(), dtos.get(i).getSubjectName() );
			assertEquals( "Nombre Tres", dtos.get(i).getStudentName() );
		}
		assertEquals( 4.5D, dtos.get(0).getBestNote(), 0.001D );
		assertEquals( 4.5D, dtos.get(1).getBestNote(), 0.001D );
	}

	@Test
	public void testShouldNotReturnBestNoteIfSubjectWithoutStudents()
	{
		StudentRepository mockStudentRepo = Mockito.mock( StudentRepository.class );
		StudentEntity entity = new StudentEntity( 
				"1", "Nombre Uno", Grade.FOUR, new ArrayList<>() );
		StudentEntity entity2 = new StudentEntity( 
				"2", "Nombre Dos", Grade.THREE, new ArrayList<>() );
		StudentEntity entity3 = new StudentEntity( 
				"3", "Nombre Tres", Grade.FIVE, new ArrayList<>() );
		List<StudentEntity> entities = Arrays.asList( entity, entity2, entity3 );
		Mockito.when( mockStudentRepo.findById( "1" ) ).thenReturn( Optional.of( entities.get( 0 ) ) );
		Mockito.when( mockStudentRepo.findById( "2" ) ).thenReturn( Optional.of( entities.get( 1 ) ) );
		Mockito.when( mockStudentRepo.findById( "3" ) ).thenReturn( Optional.of( entities.get( 2 ) ) );
		
		SubjectRepository mockSubjectRepo = Mockito.mock( SubjectRepository.class );
		SubjectEntity subject = new SubjectEntity( "1", "Subject 1", 
				Arrays.asList( 
						new EnrolledSubjectEntity( "1", "1", 4.5D ), 
						new EnrolledSubjectEntity( "1", "2", 3.8D ), 
						new EnrolledSubjectEntity( "1", "3", 4.7D ) ) );
		SubjectEntity subject2 = new SubjectEntity( "2", "Subject 2", new ArrayList<>() );
		List<SubjectEntity> subjects = Arrays.asList( subject, subject2 );
		Mockito.when( mockSubjectRepo.findAll() ).thenReturn( subjects );
		
		SubjectServiceImpl service = new SubjectServiceImpl();
		service.setStudentRepository( mockStudentRepo );
		service.setSubjectRepository( mockSubjectRepo );
		List<BestNoteDto> dtos = service.getBestNoteBySubject();
		assertEquals( 1, dtos.size() );
		assertEquals( subjects.get(0).getName(), dtos.get(0).getSubjectName() );
		assertEquals( "Nombre Tres", dtos.get(0).getStudentName() );
		assertEquals( 4.7D, dtos.get(0).getBestNote(), 0.001D );
	}
}
