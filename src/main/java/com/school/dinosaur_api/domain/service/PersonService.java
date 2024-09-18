package com.school.dinosaur_api.domain.service;

import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.model.Person;
import com.school.dinosaur_api.domain.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Transactional
    public Person savePerson(Person person) {
        boolean cpfUsed = personRepository.findByCpf(person.getCpf())
                .filter(p-> !p.equals(person)) // Se o objeto p for diferente do objeto person, retorna true, lançando a exception. Comparação do equals por ID.
                .isPresent();

        if (cpfUsed) {
            throw new BusinessException("CPF already in use");
        }

        return personRepository.save(person);
    }

    @Transactional
    public void deletePerson(Long personId) {
        personRepository.deleteById(personId);
    }
}
