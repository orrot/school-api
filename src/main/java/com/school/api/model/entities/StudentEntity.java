package com.school.api.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class StudentEntity implements Serializable {

	private static final long serialVersionUID = -3637112725401120718L;

	@Id
    private String id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Grade grade;
    
    @OneToMany( mappedBy = "studentId", fetch = FetchType.EAGER )
    private List<EnrolledSubjectEntity> subjectsTaken;
    
    public enum Grade {
        ONE, TWO, THREE, FOUR, FIVE
    }
}
