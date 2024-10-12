package com.school.dinosaur_api.api.representationmodel.input;

import com.school.dinosaur_api.domain.enums.Role;

import java.util.List;

public record UserDTO(Long id, String name, String password, List<Role> roles) {
}
