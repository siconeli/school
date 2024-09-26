package com.school.dinosaur_api.api.controller;

import com.school.dinosaur_api.api.assembler.ResponsibleAssembler;
import com.school.dinosaur_api.api.representationmodel.ResponsibleRepresentationModel;
import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.model.Responsible;
import com.school.dinosaur_api.domain.repository.ResponsibleRepository;
import com.school.dinosaur_api.domain.service.ResponsibleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/responsibles")
@RestController
public class ResponsibleController {

    private final ResponsibleService responsibleService;
    private final ResponsibleRepository responsibleRepository;
    private final ResponsibleAssembler responsibleAssembler;

    @GetMapping
    public List<ResponsibleRepresentationModel> findAll() {
        return responsibleAssembler.toCollectionRepresentationModel(responsibleRepository.findAll());
    }

    @GetMapping("/{responsibleId}")
    public ResponseEntity<ResponsibleRepresentationModel> findById(@PathVariable Long responsibleId) {
        return responsibleRepository.findById(responsibleId)
                .map(responsibleAssembler::toRepresentationModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponsibleRepresentationModel create(@Valid @RequestBody Responsible newResponsible) {
        return responsibleAssembler.toRepresentationModel(responsibleService.createResponsible(newResponsible));
    }

    @PutMapping("/{responsibleId}")
    public ResponseEntity<Responsible> update(@PathVariable Long responsibleId, @Valid @RequestBody Responsible responsible) {
        responsible.setId(responsibleId);

        return ResponseEntity.ok(responsibleService.updateResponsible(responsible));
    }

    @DeleteMapping("/{responsibleId}")
    public ResponseEntity<Void> delete(@PathVariable Long responsibleId) {
        responsibleService.deleteResponsible(responsibleId);

        return ResponseEntity.noContent().build();
    }
}
