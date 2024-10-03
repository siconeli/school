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

//    @Transactional
//    public Responsible updateResponsible(Long studentId, Responsible responsible) {
//        Responsible responsibleDb = responsibleRepository.findById(responsible.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("Responsible not found with id " + responsible.getId()));

//        boolean cpdUsed = responsibleRepository.findByCpf(responsible.getCpf())
//                .filter(r -> !r.equals(responsible))
//                .isPresent();
//
//        if (cpdUsed) {
//            throw new BusinessException("CPF already in use");
//        }

//         if (!responsibleDb.getStudent().getId().equals(studentId)) {
//             throw new BusinessException("The student has no relationship with the person responsible according to the IDs provided in the URI.");
//         }
//
//        responsible.setStudent(studentService.findStudent(studentId));
//        responsible.setRegisterDate(responsibleDb.getRegisterDate());
//        responsible.setAuthorized(responsibleDb.getAuthorized());
//        responsible.setActive(true);
//
//        return responsibleRepository.save(responsible);
//    }

    @Transactional
    public Responsible udaptePartialResponsible(Long studentId, Responsible responsibleDto) {
        Responsible responsible = responsibleRepository.findById(responsibleDto.getId())
                .orElseThrow(() -> new BusinessException("Responsible not found with id " + responsibleDto.getId()));

        boolean cpdUsed = responsibleRepository.findByCpf(responsibleDto.getCpf())
                .filter(r -> !r.equals(responsibleDto))
                .isPresent();

        if (cpdUsed) {
            throw new BusinessException("CPF already in use");
        }

        if (!responsible.getStudent().getId().equals(studentId)) {
            throw new BusinessException("The student has no relationship with the person responsible according to the IDs provided in the URI.");
        }

        if (responsibleDto.getCpf() != null) responsible.setCpf(responsibleDto.getCpf());
        if (responsibleDto.getName() != null) responsible.setName(responsibleDto.getName());
        if (responsibleDto.getTelephone() != null) responsible.setTelephone(responsibleDto.getTelephone());
        if (responsibleDto.getAddress() != null) responsible.setAddress(responsibleDto.getAddress());
        if (responsibleDto.getProfession() != null) responsible.setProfession(responsibleDto.getProfession());
        if (responsibleDto.getKinship() != null) responsible.setKinship(responsibleDto.getKinship());

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
