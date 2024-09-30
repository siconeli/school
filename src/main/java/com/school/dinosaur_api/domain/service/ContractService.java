package com.school.dinosaur_api.domain.service;

import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.model.Contract;
import com.school.dinosaur_api.domain.repository.ContractRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ContractService {
    private final ContractRepository contractRepository;

    @Transactional
    public Contract createContract(Contract newContract) {
        if (newContract.getId() != null) {
            throw new BusinessException("Must not contain the ID in the request body");
        }

        //Verificar se já existe um contrato com o ID do student informado



        return contractRepository.save(newContract);
    }

}
