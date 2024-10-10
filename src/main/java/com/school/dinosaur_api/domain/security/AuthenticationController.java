package com.school.dinosaur_api.domain.security;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("authenticate")
    public String authenticate() {
        return authenticationService.authenticate();
    }
}
