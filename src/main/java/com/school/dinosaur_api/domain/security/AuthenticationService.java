package com.school.dinosaur_api.domain.security;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public String authenticate() {
        return "token";
    }
}
