package com.school.dinosaur_api.api.representationmodel.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentOutput {
    private Long id;
    private String cpf;
    private String name;
    private Integer age;
}
