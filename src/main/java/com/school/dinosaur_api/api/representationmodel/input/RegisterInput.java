package com.school.dinosaur_api.api.representationmodel.input;

import com.school.dinosaur_api.domain.model.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterInput {
    private String login;

    private String password;

    private UserRole role;
}
