package com.school.dinosaur_api.api.representationmodel.input;

import com.school.dinosaur_api.domain.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInput {

    @Size(min = 1, max = 50)
    @NotBlank
    private String name;

    @Size(min = 1, max = 20)
    @NotBlank
    private String login;

    @Size(min = 1, max = 20)
    @NotBlank
    private String password;

    @NotNull
    private UserRole role;
}
