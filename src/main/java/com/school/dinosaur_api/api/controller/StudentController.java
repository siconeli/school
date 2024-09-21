package com.school.dinosaur_api.api.controller;

import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.model.Student;
import com.school.dinosaur_api.domain.repository.StudentRepository;
import com.school.dinosaur_api.domain.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
        return studentService.saveStudent(newStudent);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> update(@PathVariable Long studentId, @Valid @RequestBody Student student) {
        if (!studentRepository.existsById(studentId)) {
            throw new BusinessException("Student not found");
        }

        student.setId(studentId);
        return ResponseEntity.ok(studentService.saveStudent(student));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> delete(@PathVariable Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new BusinessException("Student not found");
        }
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> captureException(BusinessException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
