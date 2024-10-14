package com.school.dinosaur_api.domain.security.dto;

import com.school.dinosaur_api.domain.security.enums.Role;

public record CreateUserDTO(String name, String login, String password, Role role) {
}
