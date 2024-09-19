package com.school.dinosaur_api.api.controller;

import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.model.Responsible;
import com.school.dinosaur_api.domain.repository.ResponsibleRepository;
import com.school.dinosaur_api.domain.service.ResponsibleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

    @GetMapping
    public List<Responsible> findAll() {
        return responsibleRepository.findAll();
    }

    @GetMapping("/{responsibleId}")
    public ResponseEntity<Responsible> findById(@PathVariable Long responsibleId) {
        return responsibleRepository.findById(responsibleId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Responsible create(@Valid @RequestBody Responsible newResponsible) {
        return responsibleService.saveResponsible(newResponsible);
    }

    @PutMapping("/{responsibleId}")
    public ResponseEntity<Responsible> update(@PathVariable Long responsibleId, @Valid @RequestBody Responsible responsible) {
        if(!responsibleRepository.existsById(responsibleId)) {
            return ResponseEntity.notFound().build();
        }

        responsible.setId(responsibleId);
        Responsible updateResponsible = responsibleService.saveResponsible(responsible);
        return ResponseEntity.ok(updateResponsible);
    }

    @DeleteMapping("/{responsibleId}")
    public ResponseEntity<Void> delete(@PathVariable Long responsibleId) {
        if(!responsibleRepository.existsById(responsibleId)) {
            return ResponseEntity.notFound().build();
        }

        responsibleService.deleteResponsible(responsibleId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> captureException(BusinessException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
