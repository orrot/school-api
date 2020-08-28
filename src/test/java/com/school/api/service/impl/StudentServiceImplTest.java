package com.school.api.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.school.api.model.dto.AverageNoteDto;
import com.school.api.model.entities.EnrolledSubjectEntity;
import com.school.api.model.entities.StudentEntity;
import com.school.api.model.entities.StudentEntity.Grade;
import com.school.api.repository.StudentRepository;

/**
 * @author castrote
 *
 */
public class StudentServiceImplTest {

	@Test
	public void testShouldReturn3AveragesWhen3Students() 
	{
		StudentRepository mockRepo = Mockito.mock( StudentRepository.class );
		StudentEntity entity = new StudentEntity( 
				"1", "Nombre Uno", Grade.FOUR, Arrays.asList( 
						new EnrolledSubjectEntity( "001", "1", 4.5D ), 
						new EnrolledSubjectEntity( "002", "1", 4.0D ), 
						new EnrolledSubjectEntity( "003", "1", 3.5D ) ) );
		StudentEntity entity2 = new StudentEntity( 
				"2", "Nombre Dos", Grade.THREE, Arrays.asList( 
						new EnrolledSubjectEntity( "001", "2", 3.5D ), 
						new EnrolledSubjectEntity( "002", "2", 3.0D ), 
						new EnrolledSubjectEntity( "003", "2", 2.5D ) ) );
		StudentEntity entity3 = new StudentEntity( 
				"3", "Nombre Tres", Grade.FIVE, Arrays.asList( 
						new EnrolledSubjectEntity( "001", "3", 4.0D ), 
						new EnrolledSubjectEntity( "002", "3", 3.0D ), 
						new EnrolledSubjectEntity( "003", "3", 3.5D ) ) );
		List<StudentEntity> entities = Arrays.asList( entity, entity2, entity3 );
		Mockito.when( mockRepo.findAll() ).thenReturn( entities );
		StudentServiceImpl service = new StudentServiceImpl();
		service.setStudentRepository( mockRepo );
		List<AverageNoteDto> dtos = service.averageNotesByStudent();
		assertEquals( 3, dtos.size() );
		for ( int i = 0; i < 3 ;i++ ) {
			assertEquals( entities.get(i).getId(), dtos.get(i).getStudentId() );
			assertEquals( entities.get(i).getName(), dtos.get(i).getStudentName() );
			assertEquals( entities.get(i).getGrade(), dtos.get(i).getStudentGrade() );
		}
		assertEquals( 4.0D, dtos.get(0).getAverage(), 0.001D );
		assertEquals( 3.0D, dtos.get(1).getAverage(), 0.001D );
		assertEquals( 3.5D, dtos.get(2).getAverage(), 0.001D );
	}
	
	@Test
	public void testShouldReturnOnly2AveragesWhenStudentNoSubjects()
	{
		StudentRepository mockRepo = Mockito.mock( StudentRepository.class );
		StudentEntity entity = new StudentEntity( 
				"1", "Nombre Uno", Grade.FOUR, Arrays.asList( 
						new EnrolledSubjectEntity( "001", "1", 4.5D ), 
						new EnrolledSubjectEntity( "002", "1", 4.0D ), 
						new EnrolledSubjectEntity( "003", "1", 3.5D ) ) );
		StudentEntity entity2 = new StudentEntity( 
				"2", "Nombre Dos", Grade.THREE, Arrays.asList( 
						new EnrolledSubjectEntity( "001", "2", 3.5D ), 
						new EnrolledSubjectEntity( "002", "2", 3.0D ), 
						new EnrolledSubjectEntity( "003", "2", 2.5D ) ) );
		StudentEntity entity3 = new StudentEntity( 
				"3", "Nombre Tres", Grade.FIVE, new ArrayList<>() );
		List<StudentEntity> entities = Arrays.asList( entity, entity2, entity3 );
		Mockito.when( mockRepo.findAll() ).thenReturn( entities );
		StudentServiceImpl service = new StudentServiceImpl();
		service.setStudentRepository( mockRepo );
		List<AverageNoteDto> dtos = service.averageNotesByStudent();
		assertEquals( 2, dtos.size() );
		for ( int i = 0; i < 2 ;i++ ) {
			assertEquals( entities.get(i).getId(), dtos.get(i).getStudentId() );
			assertEquals( entities.get(i).getName(), dtos.get(i).getStudentName() );
			assertEquals( entities.get(i).getGrade(), dtos.get(i).getStudentGrade() );
		}
		assertEquals( 4.0D, dtos.get(0).getAverage(), 0.001D );
		assertEquals( 3.0D, dtos.get(1).getAverage(), 0.001D );
	}
	
	@Test
	public void testShouldReturnEmptyListWhenNoStudentsPresent()
	{
		StudentRepository mockRepo = Mockito.mock( StudentRepository.class );
		StudentEntity entity = new StudentEntity( 
				"1", "Nombre Uno", Grade.FOUR, new ArrayList<>() );
		StudentEntity entity3 = new StudentEntity( 
				"3", "Nombre Tres", Grade.FIVE, new ArrayList<>() );
		List<StudentEntity> entities = Arrays.asList( entity, entity3 );
		Mockito.when( mockRepo.findAll() ).thenReturn( entities );
		StudentServiceImpl service = new StudentServiceImpl();
		service.setStudentRepository( mockRepo );
		List<AverageNoteDto> dtos = service.averageNotesByStudent();
		assertEquals( 0, dtos.size() );
	}

}
