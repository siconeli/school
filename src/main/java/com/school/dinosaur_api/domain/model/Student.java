package com.school.dinosaur_api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Student {
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //    @CPF
    @NotBlank(message = "CPF attribute cannot be empty or null")
    @Size(max = 11)
    @Column
    private String cpf;

    @NotBlank(message = "name attribute cannot be empty or null")
    @Size(max = 60)
    @Column
    private String name;

    @Column
    private Integer age;
}
