package com.school.dinosaur_api.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Responsible {
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Boolean active;

    private LocalDate registerDate;

    @ManyToOne
    private Student student;

    private String cpf;

    private String name;

    private String telephone;

    private String address;

    private String profession;

    private Boolean authorized;

    @Enumerated(EnumType.STRING)
    private Kinship kinship;
}
