package com.school.dinosaur_api.api.representationmodel.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationInput {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

}
