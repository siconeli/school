package com.school.dinosaur_api.api.controller;

import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.model.Person;
import com.school.dinosaur_api.domain.repository.PersonRepository;
import com.school.dinosaur_api.domain.service.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/persons")
@RestController
public class PersonController {

    private final PersonService personService;
    private final PersonRepository personRepository;

    @GetMapping
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> findById(@PathVariable Long personId) {
        return personRepository.findById(personId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@Valid @RequestBody Person newPerson) {
        return personService.savePerson(newPerson);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<Person> update(@PathVariable Long personId, @Valid @RequestBody Person person) {
        if(!personRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }

        person.setId(personId);
        Person updatePerson = personService.savePerson(person);
        return ResponseEntity.ok(updatePerson);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> delete(@PathVariable Long personId) {
        if(!personRepository.existsById(personId)) {
            return ResponseEntity.notFound().build();
        }

        personService.deletePerson(personId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> captureException(BusinessException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
