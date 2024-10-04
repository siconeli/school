package com.school.dinosaur_api.domain.model;

import com.school.dinosaur_api.domain.exception.BusinessException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

    private String cpf;

    private String name;

    private String telephone;

    private String address;

    private String profession;

    private Boolean authorized;

    @Enumerated(EnumType.STRING)
    private Kinship kinship;

    @ManyToOne
    private Student student;

    public void authorizeResponsible() {
        if (getAuthorized()) {
            throw new BusinessException("The responsible is already authorized");
        }

        setAuthorized(true);
    }

    public void disauthorizeResponsible() {
        if (!getAuthorized()) {
            throw new BusinessException("The responsible is not authorized");
        }

        setAuthorized(false);
    }
}
