package com.school.dinosaur_api.api.representationmodel.input;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInput {
    @NotBlank
    @Size(max = 11)
    private String cpf;

    @NotBlank
    @Size(max = 60)
    private String name;

    private Integer age;
}
