package com.school.dinosaur_api.api.controller;

import com.school.dinosaur_api.domain.model.Student;
import com.school.dinosaur_api.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/students")
@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    @GetMapping
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
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
