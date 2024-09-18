package com.school.dinosaur_api.api.controller;

import com.school.dinosaur_api.domain.model.Person;
import com.school.dinosaur_api.domain.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/persons")
@RestController
public class PersonController {

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
    public Person create(@RequestBody Person newPerson) {
        return personRepository.save(newPerson);
    }

    @PutMapping("/{personId}")
    public void update(@RequestBody Person person, @PathVariable Long personId) {
        return;
    }
}
