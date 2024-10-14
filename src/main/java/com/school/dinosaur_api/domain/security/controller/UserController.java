package com.school.dinosaur_api.domain.security.controller;

import com.school.dinosaur_api.domain.security.dto.CreateUserDTO;
import com.school.dinosaur_api.domain.security.dto.JwtTokenDTO;
import com.school.dinosaur_api.domain.security.dto.LoginUserDTO;
import com.school.dinosaur_api.domain.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/auth/users")
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> loginUser(@RequestBody LoginUserDTO loginUserDTO) {
        JwtTokenDTO token = userService.authenticateUser(loginUserDTO);
        return ResponseEntity.ok(token);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody CreateUserDTO createUserDTO) {
        userService.saveUser(createUserDTO);
    }
}
