package com.school.dinosaur_api.api.controller;

import com.school.dinosaur_api.api.assembler.ResponsibleAssembler;
import com.school.dinosaur_api.api.representationmodel.input.ResponsibleInput;
import com.school.dinosaur_api.api.representationmodel.output.ResponsibleOutput;
import com.school.dinosaur_api.domain.model.Responsible;
import com.school.dinosaur_api.domain.model.Student;
import com.school.dinosaur_api.domain.repository.ResponsibleRepository;
import com.school.dinosaur_api.domain.service.ResponsibleService;
import com.school.dinosaur_api.domain.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/students/{studentId}/responsibles")
@RestController
public class ResponsibleController {
    private final ResponsibleService responsibleService;
    private final ResponsibleRepository responsibleRepository;
    private final ResponsibleAssembler responsibleAssembler;
    private final StudentService studentService;

    @GetMapping
    public List<ResponsibleOutput> findAll(@PathVariable Long studentId) {
        Student student = studentService.findStudent(studentId);

        return responsibleAssembler.toCollectionRepresentationModel(student.getResponsibles());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponsibleOutput create(@PathVariable Long studentId, @Valid @RequestBody ResponsibleInput responsibleInput) {
        Responsible responsible = responsibleAssembler.toEntity(responsibleInput);

        return responsibleAssembler.toRepresentationModel(responsibleService.createResponsible(studentId, responsible));
    }

    @PutMapping("/{responsibleId}")
    public ResponseEntity<ResponsibleOutput> update(@PathVariable Long studentId, @PathVariable Long responsibleId, @Valid @RequestBody ResponsibleInput responsibleInput) {
        Responsible responsible = responsibleAssembler.toEntity(responsibleInput);
        responsible.setId(responsibleId);
        return ResponseEntity.ok(responsibleAssembler.toRepresentationModel(responsibleService.updateResponsible(studentId, responsible)));
    }

    @DeleteMapping("/{responsibleId}")
    public ResponseEntity<Void> delete(@PathVariable Long responsibleId) {
        responsibleService.deleteResponsible(responsibleId);

        return ResponseEntity.noContent().build();
    }
}
