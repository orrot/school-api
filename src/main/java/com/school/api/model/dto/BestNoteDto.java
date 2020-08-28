/**
 * 
 */
package com.school.api.model.dto;

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
public class BestNoteDto 
{
	private String subjectName;
	private String studentName;
	private Double bestNote;
	
}
