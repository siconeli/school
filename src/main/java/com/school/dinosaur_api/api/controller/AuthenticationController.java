package com.school.dinosaur_api.api.controller;

import com.school.dinosaur_api.api.representationmodel.input.AuthenticationInput;
import com.school.dinosaur_api.api.representationmodel.input.RegisterInput;
import com.school.dinosaur_api.domain.model.User;
import com.school.dinosaur_api.domain.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody AuthenticationInput authenticationInput) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(authenticationInput.getLogin(), authenticationInput.getPassword());
        var auth = authenticationManager.authenticate(userNamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterInput registerInput) {
        if(userRepository.findByLogin(registerInput.getLogin()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = passwordEncoder.encode(registerInput.getPassword());

        User newUser = new User(registerInput.getLogin(), encryptedPassword, registerInput.getRole());

        userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
