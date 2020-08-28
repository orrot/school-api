package com.school.api.model.entities.key;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EnrolledSubjectEntityId implements Serializable {

	private static final long serialVersionUID = -6626419491660856508L;
	private String subjectId;
    private String studentId;
    //private Double note;

}
