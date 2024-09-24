package com.school.dinosaur_api.domain.service;

import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.exception.ResourceNotFoundException;
import com.school.dinosaur_api.domain.model.Responsible;
import com.school.dinosaur_api.domain.model.Student;
import com.school.dinosaur_api.domain.repository.ResponsibleRepository;
import com.school.dinosaur_api.domain.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ResponsibleService {

    private final ResponsibleRepository responsibleRepository;
    private  final StudentService studentService;

    @Transactional
    public Responsible createResponsible(Responsible newResponsible) {
        if (newResponsible.getId() != null) {
            throw new BusinessException("Must not contain the ID in the request body");
        }

        boolean cpfUsed = responsibleRepository.findByCpf(newResponsible.getCpf())
                .filter(r -> !r.equals(newResponsible)) // Se o objeto r for diferente do objeto responsible, retorna true, lançando a exception. Comparação do equals por ID.
                .isPresent();

        if (cpfUsed) {
            throw new BusinessException("CPF already in use");
        }

        newResponsible.setStudent(studentService.findStudent(newResponsible.getStudent().getId()));

        newResponsible.setAuthorized(true);

        return responsibleRepository.save(newResponsible);
    }

    @Transactional
    public Responsible updateResponsible(Responsible responsible) {
        if(!responsibleRepository.existsById(responsible.getId())) {
            throw new ResourceNotFoundException("Responsible not found with id " + responsible.getId());
        }

        boolean cpdUsed = responsibleRepository.findByCpf(responsible.getCpf())
                .filter(r -> !r.equals(responsible))
                .isPresent();

        if (cpdUsed) {
            throw new BusinessException("CPF already in use");
        }

        studentService.findStudent(responsible.getStudent().getId());

        responsible.setAuthorized(true);

        return responsibleRepository.save(responsible);
    }

    @Transactional
    public void deleteResponsible(Long responsibleId) {
        if(!responsibleRepository.existsById(responsibleId)) {
            throw new ResourceNotFoundException("Responsible not found with id " + responsibleId);
        }

        responsibleRepository.deleteById(responsibleId);
    }
}
