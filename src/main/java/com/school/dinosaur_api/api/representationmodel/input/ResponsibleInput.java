package com.school.dinosaur_api.api.representationmodel.input;

import com.school.dinosaur_api.domain.model.Kinship;
import com.school.dinosaur_api.domain.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import jakarta.validation.groups.Default;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsibleInput {
    @NotBlank
    @Size(min = 11, max = 11, groups = {Default.class, ValidationGroups.UpdateValidation.class})
    private String cpf;

    @NotBlank
    @Size(min = 1, max = 60, groups = {Default.class, ValidationGroups.UpdateValidation.class})
    private String name;

    @NotBlank
    @Size(min = 1, max = 20, groups = {Default.class, ValidationGroups.UpdateValidation.class})
    private String telephone;

    @NotBlank
    @Size(min = 1, max = 255, groups = {Default.class, ValidationGroups.UpdateValidation.class})
    private String address;

    @Size(min = 1, max = 60, groups = {Default.class, ValidationGroups.UpdateValidation.class})
    private String profession;

    @NotNull
    private Kinship kinship;
}
