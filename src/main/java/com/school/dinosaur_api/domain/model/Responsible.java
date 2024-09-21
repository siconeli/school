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
public class Responsible {
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    private Student student;

//        @CPF
    @NotBlank(message = "CPF attribute cannot be empty or null")
    @Size(max = 11)
    @Column
    private String cpf;

    @NotBlank(message = "name attribute cannot be empty or null")
    @Size(max = 60)
    @Column
    private String name;

    @NotBlank(message = "telephone attribute cannot be empty or null")
    @Size(max = 20)
    @Column
    private String telephone;

    @NotBlank(message = "address attribute cannot be empty or null")
    @Size(max = 255)
    @Column
    private String address;

    @Size(max = 60)
    @Column
    private String profession;

    @Column
    private Boolean authorized;

    @Enumerated(EnumType.STRING)
    @Column
    private Kinship kinship;
}
