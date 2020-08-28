package com.school.api.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.school.api.model.dto.Subject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subject")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class SubjectEntity implements Serializable {

	private static final long serialVersionUID = 8910654720955478206L;

	@Id
    private String id;

    @Column
    private String name;
    
    @OneToMany( mappedBy = "subjectId", fetch = FetchType.EAGER )
    private List<EnrolledSubjectEntity> studentsTakingSubject;

    // This mapping can be done in better ways. For agile development, it was done at this way
    public Subject toDTO() {
        return Subject.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }

}
