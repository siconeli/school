package com.school.dinosaur_api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.school.dinosaur_api.domain.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
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

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.StudentId.class)
    @NotNull
    @ManyToOne
    private Student student;

//        @CPF
    @NotBlank
    @Size(max = 11)
    @Column
    private String cpf;

    @NotBlank
    @Size(max = 60)
    @Column
    private String name;

    @NotBlank
    @Size(max = 20)
    @Column
    private String telephone;

    @NotBlank
    @Size(max = 255)
    @Column
    private String address;

    @Size(max = 60)
    @Column
    private String profession;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column
    private Boolean authorized;

    @Enumerated(EnumType.STRING)
    @Column
    private Kinship kinship;
}
