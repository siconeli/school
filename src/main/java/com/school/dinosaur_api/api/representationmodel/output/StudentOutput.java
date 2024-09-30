package com.school.dinosaur_api.api.representationmodel.output;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentOutput {
    private Long id;

    private Boolean active;

    private LocalDate registerDate;

    private String cpf;

    private String name;

    private Integer age;
}
