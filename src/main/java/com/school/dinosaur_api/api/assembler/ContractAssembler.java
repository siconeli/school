package com.school.dinosaur_api.api.assembler;

import com.school.dinosaur_api.api.representationmodel.input.ContractInput;
import com.school.dinosaur_api.api.representationmodel.output.ContractOutput;
import com.school.dinosaur_api.domain.model.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Component
public class ContractAssembler {
    private final ModelMapper modelMapper;

    public Contract toEntity(ContractInput contractInput) {
        return modelMapper.map(contractInput, Contract.class);
    }

    public ContractOutput toRepresentationModel(Contract contract) {
        return modelMapper.map(contract, ContractOutput.class);
    }

    public List<ContractOutput> toCollectionRepresentationModel(List<Contract> contractList) {
        return contractList
                .stream()
                .map(this::toRepresentationModel)
                .toList();
    }
}
