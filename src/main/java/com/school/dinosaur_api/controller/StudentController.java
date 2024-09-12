package com.school.dinosaur_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/students")
@RestController
public class StudentController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public String findAllStudents() {
        return "All students";
    }
}
