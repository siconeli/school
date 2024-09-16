package com.school.dinosaur_api.api.controller;

import com.school.dinosaur_api.domain.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/students")
@RestController
public class StudentController {

//    private final StudentRepository studentRepository;

    @GetMapping
    public String findAllStudents() {
//        return studentRepository.findAll();
        return "All students";
    }

    @GetMapping("/{studentId}")
    public void findStudent(@PathVariable Long studentId) {
        return;
    }

    @PostMapping
    public void createStudent(@RequestBody Student student) {
        return;
    }

    @PutMapping
    public void updateStudent() {
        return;
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
        return;
    }


}
