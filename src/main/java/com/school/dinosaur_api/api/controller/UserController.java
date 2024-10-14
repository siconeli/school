package com.school.dinosaur_api.api.controller;

import com.school.dinosaur_api.api.representationmodel.input.CreateUserDTO;
import com.school.dinosaur_api.api.representationmodel.input.JwtTokenDTO;
import com.school.dinosaur_api.api.representationmodel.input.LoginUserDTO;
import com.school.dinosaur_api.domain.service.UserService;
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
