package com.school.dinosaur_api.domain.service;

import com.school.dinosaur_api.api.helper.IgnoreNullBeanUtilsBean;
import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.exception.ResourceNotFoundException;
import com.school.dinosaur_api.domain.model.Responsible;
import com.school.dinosaur_api.domain.model.Student;
import com.school.dinosaur_api.domain.repository.ResponsibleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

@AllArgsConstructor
@Service
public class ResponsibleService {

    private final ResponsibleRepository responsibleRepository;
    private  final StudentService studentService;
    private final IgnoreNullBeanUtilsBean ignoreNullBeanUtilsBean;

    public Responsible findResponsible(Long responsibleId) {
        return responsibleRepository.findById(responsibleId)
                .orElseThrow(() -> new BusinessException("Responsible not found with id " + responsibleId));
    }

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
    public Responsible udaptePartialResponsible(Long studentId, Responsible responsibleDto) {
        Responsible responsible = this.findResponsible(responsibleDto.getId());

        boolean cpdUsed = responsibleRepository.findByCpf(responsibleDto.getCpf())
                .filter(r -> !r.equals(responsibleDto))
                .isPresent();

        if (cpdUsed) {
            throw new BusinessException("CPF already in use");
        }

        if (!responsible.getStudent().getId().equals(studentId)) {
            throw new BusinessException("The student has no relationship with the person responsible according to the IDs provided in the URI.");
        }

        try {
            ignoreNullBeanUtilsBean.copyProperties(responsible, responsibleDto); // Copia os atributos do responsibleDto no responsible, somente os não nulos.
        }
        catch(IllegalAccessException e) {
            throw new BusinessException("Message: " + e.getMessage());
        }
        catch(InvocationTargetException e) {
            throw new BusinessException(e.getMessage());
        }

        return responsibleRepository.save(responsible);
    }

    @Transactional
    public void deleteResponsible(Long studentId, Long responsibleId) {
        Responsible responsible = this.findResponsible(responsibleId);

        if (!responsible.getStudent().getId().equals(studentId)) {
            throw new BusinessException("The student has no relationship with the person responsible according to the IDs provided in the URI.");
        }

        Student student = studentService.findStudent(studentId);
        student.removeResponsible(responsible);
    }
}
