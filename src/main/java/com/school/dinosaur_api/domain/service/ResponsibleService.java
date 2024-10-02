package com.school.dinosaur_api.domain.service;

import com.school.dinosaur_api.api.assembler.ResponsibleAssembler;
import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.exception.ResourceNotFoundException;
import com.school.dinosaur_api.domain.model.Responsible;
import com.school.dinosaur_api.domain.model.Student;
import com.school.dinosaur_api.domain.repository.ResponsibleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ResponsibleService {

    private final ResponsibleRepository responsibleRepository;
    private  final StudentService studentService;

    @Transactional
    public Responsible createResponsible(Long studentId, Responsible newResponsible) {
        boolean cpfUsed = responsibleRepository.findByCpf(newResponsible.getCpf())
                .filter(r -> !r.equals(newResponsible))
                .isPresent();

        if (cpfUsed) {
            throw new BusinessException("CPF already in use");
        }

        Student student = studentService.findStudent(studentId);
        return student.addResponsible(newResponsible);
    }

    @Transactional
    public Responsible updateResponsible(Long studentId, Responsible responsible) {
        Responsible responsibleDb = responsibleRepository.findById(responsible.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Responsible not found with id " + responsible.getId()));

        boolean cpdUsed = responsibleRepository.findByCpf(responsible.getCpf())
                .filter(r -> !r.equals(responsible))
                .isPresent();

        if (cpdUsed) {
            throw new BusinessException("CPF already in use");
        }

        // VERIFICAR SE O studentId PASSADO TEM RELAÇÃO COM O RESPONSIBLE
        // se for diferente, lançar uma exception informando que o id da uri não é o mesmo id do cadastro no banco de dados.
//         if (!responsibleDb.getStudent().getId().equals(studentId)) {
//             throw new BusinessException("id de student diferente do banco!");
//         }


        responsible.setStudent(studentService.findStudent(studentId));
        responsible.setRegisterDate(responsibleDb.getRegisterDate());
        responsible.setAuthorized(responsibleDb.getAuthorized());
        responsible.setActive(true);

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
