package com.school.dinosaur_api.domain.service;

import com.school.dinosaur_api.api.helper.IgnoreNullBeanUtilsBean;
import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.exception.ResourceNotFoundException;
import com.school.dinosaur_api.domain.model.Contract;
import com.school.dinosaur_api.domain.model.Student;
import com.school.dinosaur_api.domain.repository.ContractRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

@AllArgsConstructor
@Service
public class ContractService {
    private final ContractRepository contractRepository;
    private final StudentService studentService;
    private final IgnoreNullBeanUtilsBean ignoreNullBeanUtilsBean;

    @Transactional
    public Contract createContract(Long studentId, Contract contractDto) {
        Student student = studentService.findStudent(studentId);

        if (contractRepository.existsByStudent(student)) {
            throw new BusinessException("There is already a contract for the student provided");
        }

        contractDto.setActive(true);
        contractDto.setRegisterDate(LocalDate.now());
        contractDto.setStudent(student);

        return contractRepository.save(contractDto);
    }

    @Transactional
    public Contract updatePartialContract(Long studentId, Contract contractDto) {
        Contract contract = contractRepository.findById(contractDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id " + contractDto.getId()));

        boolean link = contractRepository.findByStudentId(studentId)
                .filter(c -> !c.equals(contractDto))
                .isPresent();

        if (link) {
            throw new BusinessException("There is already a contract for the student provided");
        }

        studentService.findStudent(studentId);

        try {
          ignoreNullBeanUtilsBean.copyProperties(contract, contractDto);
        }
        catch(IllegalAccessException e) {
            throw new BusinessException("Message: " + e.getMessage());
        }
        catch(InvocationTargetException e) {
            throw new BusinessException(e.getMessage());
        }

        return contractRepository.save(contract);
    }
}
