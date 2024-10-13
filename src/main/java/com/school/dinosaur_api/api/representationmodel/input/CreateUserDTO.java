package com.school.dinosaur_api.api.representationmodel.input;

import com.school.dinosaur_api.domain.enums.Role;

public record CreateUserDTO(String name, String login, String password, Role role) {
}
