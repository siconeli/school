package com.school.dinosaur_api.api.controller;

import com.school.dinosaur_api.domain.model.Student;
import com.school.dinosaur_api.domain.repository.StudentRepository;
import com.school.dinosaur_api.domain.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/students")
@RestController
public class StudentController {
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @GetMapping
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> findById(@PathVariable Long studentId) {
        return studentRepository.findById(studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Student create(@Valid @RequestBody Student newStudent) {
        return studentService.createStudent(newStudent);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> update(@PathVariable Long studentId, @Valid @RequestBody Student student) {
        student.setId(studentId);
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> delete(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}
