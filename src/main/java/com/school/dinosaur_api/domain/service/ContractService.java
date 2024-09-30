package com.school.dinosaur_api.domain.service;

import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.model.Contract;
import com.school.dinosaur_api.domain.repository.ContractRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class ContractService {
    private final ContractRepository contractRepository;
    private final StudentService studentService;

    @Transactional
    public Contract createContract(Contract newContract) {
        if (newContract.getId() != null) {
            throw new BusinessException("Must not contain the ID in the request body");
        }

        if (contractRepository.existsByStudent(newContract.getStudent())) {
            throw new BusinessException("There is already a contract for the student provided");
        }

        newContract.setActive(true);
        newContract.setRegisterDate(LocalDate.now());
        newContract.setStudent(studentService.findStudent(newContract.getStudent().getId()));

        return contractRepository.save(newContract);
    }

}
