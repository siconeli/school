package com.school.dinosaur_api.api.controller;

import com.school.dinosaur_api.api.assembler.ContractAssembler;
import com.school.dinosaur_api.api.representationmodel.input.ContractInput;
import com.school.dinosaur_api.api.representationmodel.output.ContractOutput;
import com.school.dinosaur_api.domain.model.Contract;
import com.school.dinosaur_api.domain.repository.ContractRepository;
import com.school.dinosaur_api.domain.service.ContractService;
import com.school.dinosaur_api.domain.validation.ValidationGroups;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@RequestMapping("/students/{studentId}/contracts")
@RestController
public class ContractController {
    private final ContractRepository contractRepository;
    private final ContractAssembler contractAssembler;
    private final ContractService contractService;

    @GetMapping
    public ResponseEntity<ContractOutput> findById(@PathVariable Long studentId) {
        return contractRepository.findByStudentId(studentId)
                .map(contractAssembler::toRepresentationModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ContractOutput create(@PathVariable Long studentId, @Valid @RequestBody ContractInput contractInput) {
        Contract contractDto = contractAssembler.toEntity(contractInput);

        return contractAssembler.toRepresentationModel(contractService.createContract(studentId, contractDto));
    }

    @PatchMapping("/{contractId}")
    public ResponseEntity<ContractOutput> update(@PathVariable Long studentId, @PathVariable Long contractId, @Validated(ValidationGroups.UpdateValidation.class) @RequestBody ContractInput contractInput) {
        Contract contractDto = contractAssembler.toEntity(contractInput);
        contractDto.setId(contractId);

        return ResponseEntity.ok(contractAssembler.toRepresentationModel(contractService.updatePartialContract(studentId, contractDto)));

    }
}
