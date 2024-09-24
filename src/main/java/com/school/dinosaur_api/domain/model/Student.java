package com.school.dinosaur_api.domain.model;

import com.school.dinosaur_api.domain.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Student {
    @NotNull(groups = ValidationGroups.StudentId.class)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //    @CPF
    @NotBlank
    @Size(max = 11)
    @Column
    private String cpf;

    @NotBlank
    @Size(max = 60)
    @Column
    private String name;

    @Column
    private Integer age;
}
