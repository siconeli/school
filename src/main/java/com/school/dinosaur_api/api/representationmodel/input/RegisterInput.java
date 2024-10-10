package com.school.dinosaur_api.api.representationmodel.input;

import com.school.dinosaur_api.domain.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterInput {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotNull
    private UserRole role;
}
