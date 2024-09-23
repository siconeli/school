package com.school.dinosaur_api.domain.service;

import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.model.Responsible;
import com.school.dinosaur_api.domain.model.Student;
import com.school.dinosaur_api.domain.repository.ResponsibleRepository;
import com.school.dinosaur_api.domain.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ResponsibleService {

    private final ResponsibleRepository responsibleRepository;
    private final StudentService studentService;

    @Transactional
    public Responsible saveResponsible(Responsible responsible) {
        if (responsible.getId() != null) {
            throw new BusinessException("Must not contain the ID in the request body");
        }

        boolean cpfUsed = responsibleRepository.findByCpf(responsible.getCpf())
                .filter(r -> !r.equals(responsible)) // Se o objeto r for diferente do objeto responsible, retorna true, lançando a exception. Comparação do equals por ID.
                .isPresent();

        if (cpfUsed) {
            throw new BusinessException("CPF already in use");
        }

        responsible.setStudent(studentService.findStudent(responsible.getStudent().getId()));
        responsible.setAuthorized(true);
        return responsibleRepository.save(responsible);
    }

    @Transactional
    public void deleteResponsible(Long responsibleId) {
        responsibleRepository.deleteById(responsibleId);
    }
}
