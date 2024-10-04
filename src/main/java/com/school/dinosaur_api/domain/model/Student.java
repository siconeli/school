package com.school.dinosaur_api.domain.model;

import com.school.dinosaur_api.api.assembler.ResponsibleAssembler;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Student {
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Boolean active;

    private LocalDate registerDate;

    private String cpf;

    private String name;

    private Integer age;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Contract contract;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Responsible> responsibles = new ArrayList<>();

    public Responsible addResponsible(Responsible responsible) {
        responsible.setActive(true);
        responsible.setRegisterDate(LocalDate.now());
        responsible.setAuthorized(true);
        responsible.setStudent(this);
        getResponsibles().add(responsible); // Ao adicionar o responsible já realiza o save, devido o Cascade.

        return responsible;
    }

    public void removeResponsible(Responsible responsible) {
        getResponsibles().remove(responsible);
    }
}
