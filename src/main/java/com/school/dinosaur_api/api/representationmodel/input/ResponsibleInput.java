package com.school.dinosaur_api.api.representationmodel.input;

import com.school.dinosaur_api.domain.model.Kinship;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsibleInput {
    @NotBlank
    @Size(max = 11)
    private String cpf;

    @NotBlank
    @Size(max = 60)
    private String name;

    @NotBlank
    @Size(max = 20)
    private String telephone;

    @NotBlank
    @Size(max = 255)
    private String address;

    @Size(max = 60)
    private String profession;

    @NotNull
    private Kinship kinship;
}
