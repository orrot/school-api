package com.school.api.model.entities;

import com.school.api.model.entities.key.EnrolledSubjectEntityId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "enrolled_subject")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(EnrolledSubjectEntityId.class)
public class EnrolledSubjectEntity implements Serializable {

	private static final long serialVersionUID = 6928015409244851878L;

	@Id
    @Column(name = "subject_id")
    private String subjectId;

    @Id
    @Column(name = "student_id")
    private String studentId;

    @Column
    private Double note;
}
