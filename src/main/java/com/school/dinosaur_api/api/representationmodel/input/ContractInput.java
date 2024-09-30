package com.school.dinosaur_api.api.representationmodel.input;

import com.school.dinosaur_api.domain.model.Period;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ContractInput {
    @Valid
    @NotNull
    private StudentIdInput student;

    @NotNull
    private Period contractedPeriod;

    @NotNull
    private Date dateInput;

    @NotNull
    private Date dateOutput;
}
