package com.school.dinosaur_api.api.representationmodel.output;

import com.school.dinosaur_api.domain.model.Kinship;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResponsibleOutput {
    private Long id;

    private Boolean active;

    private LocalDate registerDate;

    private StudentSummary student;

    private String cpf;

    private String name;

    private String tel;

    private String address;

    private String profession;

    private Boolean authorized;

    private Kinship kinship;
}
