package com.school.api.model.dto;

import com.school.api.model.entities.StudentEntity.Grade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author castrote
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto 
{
	private String studentId;
	private String studentName;
	private Grade studentGrade;
}
