package com.school.dinosaur_api.domain.repository;

import com.school.dinosaur_api.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByCpfContaining(String cpf);
}
