package com.school.dinosaur_api.api.representationmodel.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationInput {
    private String login;

    private String password;

}
