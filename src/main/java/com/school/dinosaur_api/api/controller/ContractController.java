package com.school.dinosaur_api.api.controller;

import com.school.dinosaur_api.api.assembler.ContractAssembler;
import com.school.dinosaur_api.api.representationmodel.input.ContractInput;
import com.school.dinosaur_api.api.representationmodel.output.ContractOutput;
import com.school.dinosaur_api.domain.model.Contract;
import com.school.dinosaur_api.domain.repository.ContractRepository;
import com.school.dinosaur_api.domain.service.ContractService;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@RequestMapping("/contracts")
@RestController
public class ContractController {
    private final ContractRepository contractRepository;
    private final ContractAssembler contractAssembler;
    private final ContractService contractService;

    @GetMapping
    public List<ContractOutput> findAll() {
        return contractAssembler.toCollectionRepresentationModel(contractRepository.findAll());
    }

    @GetMapping("/{contractId}")
    public ResponseEntity<ContractOutput> findById(@PathVariable Long contractId) {
        return contractRepository.findById(contractId)
                .map(contractAssembler::toRepresentationModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ContractOutput create(@RequestBody ContractInput contractInput) {
        Contract contract = contractAssembler.toEntity(contractInput);

        return contractAssembler.toRepresentationModel(contractService.createContract(contract));
    }

    @PutMapping("/{contractId}")
    public ResponseEntity<ContractOutput> update(@PathVariable Long contractId, @RequestBody ContractInput contractInput) {
        Contract contract = contractAssembler.toEntity(contractInput);

        contract.setId(contractId);

        return ResponseEntity.ok(contractAssembler.toRepresentationModel(contractService.updateContract(contract)));

    }
}
