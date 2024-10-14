package com.school.dinosaur_api.domain.service;

import com.school.dinosaur_api.api.representationmodel.input.CreateUserDTO;
import com.school.dinosaur_api.api.representationmodel.input.JwtTokenDTO;
import com.school.dinosaur_api.api.representationmodel.input.LoginUserDTO;
import com.school.dinosaur_api.domain.model.ModelRole;
import com.school.dinosaur_api.domain.model.ModelUserDetails;
import com.school.dinosaur_api.domain.model.User;
import com.school.dinosaur_api.domain.repository.UserRepository;
import com.school.dinosaur_api.domain.security.SecurityConfig;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    private SecurityConfig securityConfig;

    private AuthenticationManager authenticationManager;

    private JwtTokenService jwtTokenService;

    @Transactional
    public void saveUser(CreateUserDTO createUserDTO) {
        User newUser = User.builder()
                .name(createUserDTO.name())
                .login(createUserDTO.login())
                .password(securityConfig.passwordEncoder().encode(createUserDTO.password()))
                .roles(List.of(ModelRole.builder().name(createUserDTO.role()).build()))
                .build();

        userRepository.save(newUser);
    }

    @Transactional
    public JwtTokenDTO authenticateUser(LoginUserDTO loginUserDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUserDTO.login(), loginUserDTO.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        ModelUserDetails modelUserDetails = (ModelUserDetails) authentication.getPrincipal();

        return new JwtTokenDTO(jwtTokenService.generateToken(modelUserDetails));
    }
}
