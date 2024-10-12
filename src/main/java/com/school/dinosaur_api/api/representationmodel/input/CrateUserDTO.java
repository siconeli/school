package com.school.dinosaur_api.api.representationmodel.input;

import com.school.dinosaur_api.domain.enums.Role;

public record CrateUserDTO(String name, String login, String password, Role role) {
}
