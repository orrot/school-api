package com.school.api.model.entities;

import com.school.api.model.dto.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "subject")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class SubjectEntity implements Serializable {

    @Id
    private String id;

    @Column
    private String name;

    // This mapping can be done in better ways. For agile development, it was done at this way
    public Subject toDTO() {
        return Subject.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }

}
