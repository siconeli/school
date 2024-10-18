package com.school.dinosaur_api.api.representationmodel.input;

import com.school.dinosaur_api.domain.validation.ValidationGroups;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StudentInput {
    @NotBlank
    @Size(min = 11, max = 11, groups = {Default.class, ValidationGroups.UpdateValidation.class})
    private String cpf;

    @NotBlank
    @Size(min = 1, max = 60, groups = {Default.class, ValidationGroups.UpdateValidation.class})
    private String name;

    @Min(value = 1, groups = {Default.class, ValidationGroups.UpdateValidation.class})
    @NotNull
    private Integer age;
}
