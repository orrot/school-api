package com.school.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.api.model.entities.StudentEntity;

/**
 * @author castrote
 *
 */
public interface StudentRepository extends JpaRepository<StudentEntity, String> {

	List<StudentEntity> findByNameStartingWith( String prefix );
	
	@Query( value = "SELECT * FROM student WHERE name LIKE ?1%", nativeQuery = true )
	List<StudentEntity> findByNamePrefix( String prefix );

}
