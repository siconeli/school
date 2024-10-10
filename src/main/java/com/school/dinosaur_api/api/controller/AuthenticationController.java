package com.school.dinosaur_api.api.controller;

import com.school.dinosaur_api.api.representationmodel.input.AuthenticationInput;
import com.school.dinosaur_api.api.representationmodel.input.RegisterInput;
import com.school.dinosaur_api.domain.service.AuthenticationSecurityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationSecurityService authenticationSecurityService;


    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody AuthenticationInput authenticationInput) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(authenticationInput.getLogin(), authenticationInput.getPassword());
        var auth = authenticationManager.authenticate(userNamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterInput registerInput) {
        authenticationSecurityService.registerUser(registerInput);

        return ResponseEntity.ok().build();
    }
}
