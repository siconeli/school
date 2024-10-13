package com.school.dinosaur_api.domain.service;

import com.school.dinosaur_api.api.representationmodel.input.CreateUserDTO;
import com.school.dinosaur_api.domain.model.ModelRole;
import com.school.dinosaur_api.domain.model.User;
import com.school.dinosaur_api.domain.repository.UserRepository;
import com.school.dinosaur_api.domain.security.SecurityConfig;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    private SecurityConfig securityConfig;

    private AuthenticationManager authenticationManager;

    private JwtTokenService jwtTokenService;

    public void salvarUsuario(CreateUserDTO createUserDTO) {
        User newUser = User.builder()
                .name(createUserDTO.name())
                .password(securityConfig.passwordEncoder().encode(createUserDTO.password()))
                .roles(List.of(ModelRole.builder().name(createUserDTO.role()).build()))
                .build();

        userRepository.save(newUser);
    }
}
