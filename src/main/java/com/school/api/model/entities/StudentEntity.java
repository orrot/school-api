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
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class StudentEntity implements Serializable {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Grade grade;

    public enum  Grade {
        ONE, TWO, THREE, FOUR, FIVE
    }
}
