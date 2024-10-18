package com.school.dinosaur_api.api.controller;

import com.school.dinosaur_api.api.assembler.StudentAssembler;
import com.school.dinosaur_api.api.representationmodel.input.StudentInput;
import com.school.dinosaur_api.api.representationmodel.output.StudentOutput;
import com.school.dinosaur_api.domain.model.Student;
import com.school.dinosaur_api.domain.repository.StudentRepository;
import com.school.dinosaur_api.domain.service.StudentService;
import com.school.dinosaur_api.domain.validation.ValidationGroups;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/students")
@RestController
public class StudentController {
    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final StudentAssembler studentAssembler;

    @GetMapping
    public List<StudentOutput> findAll() {
        return studentAssembler.toCollectionRepresentationModel(studentRepository.findAll());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentOutput> findById(@PathVariable Long studentId) {
        return studentRepository.findById(studentId)
                .map(studentAssembler::toRepresentationModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public StudentOutput create(@Valid @RequestBody StudentInput studentInput) {
        Student student = studentAssembler.toEntity(studentInput);
        return studentAssembler.toRepresentationModel(studentService.createStudent(student));
    }

    @PatchMapping("/{studentId}")
    public ResponseEntity<StudentOutput> update(@PathVariable Long studentId, @Validated(ValidationGroups.UpdateValidation.class) @RequestBody StudentInput studentInput) {
        Student student = studentAssembler.toEntity(studentInput);
        student.setId(studentId);

        return ResponseEntity.ok(studentAssembler.toRepresentationModel(studentService.updatePartialStudent(student)));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> delete(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}
