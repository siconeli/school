package com.school.dinosaur_api.api.representationmodel.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentIdInput {
    @NotNull
    private Long id;
}
