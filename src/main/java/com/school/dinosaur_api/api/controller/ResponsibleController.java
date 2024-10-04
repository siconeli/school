package com.school.dinosaur_api.api.controller;

import com.school.dinosaur_api.api.assembler.ResponsibleAssembler;
import com.school.dinosaur_api.api.representationmodel.input.ResponsibleInput;
import com.school.dinosaur_api.api.representationmodel.output.ResponsibleOutput;
import com.school.dinosaur_api.domain.model.Responsible;
import com.school.dinosaur_api.domain.model.Student;
import com.school.dinosaur_api.domain.service.ResponsibleService;
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
@RequestMapping("/students/{studentId}/responsibles")
@RestController
public class ResponsibleController {
    private final ResponsibleService responsibleService;
    private final ResponsibleAssembler responsibleAssembler;
    private final StudentService studentService;

    @GetMapping
    public List<ResponsibleOutput> findAllAuthorized(@PathVariable Long studentId) {
        Student student = studentService.findStudent(studentId);

        List<Responsible> responsibleAuthorized = student.getResponsibles()
                .stream()
                .filter(Responsible::getAuthorized)
                .toList();

        return responsibleAssembler.toCollectionRepresentationModel(responsibleAuthorized);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponsibleOutput create(@PathVariable Long studentId, @Valid @RequestBody ResponsibleInput responsibleInput) {
        Responsible responsibleDto = responsibleAssembler.toEntity(responsibleInput);

        return responsibleAssembler.toRepresentationModel(responsibleService.createResponsible(studentId, responsibleDto));
    }

    @PatchMapping("/{responsibleId}")
    public ResponseEntity<ResponsibleOutput> update(@PathVariable Long studentId, @PathVariable Long responsibleId, @Validated(ValidationGroups.UpdateValidation.class) @RequestBody ResponsibleInput responsibleInput) {
        Responsible responsibleDto = responsibleAssembler.toEntity(responsibleInput);
        responsibleDto.setId(responsibleId);

        return ResponseEntity.ok(responsibleAssembler.toRepresentationModel(responsibleService.udaptePartialResponsible(studentId, responsibleDto)));
    }

    @DeleteMapping("/{responsibleId}")
    public ResponseEntity<Void> delete(@PathVariable Long studentId, @PathVariable Long responsibleId) {
        responsibleService.deleteResponsible(studentId, responsibleId);

        return ResponseEntity.noContent().build();
    }
}
