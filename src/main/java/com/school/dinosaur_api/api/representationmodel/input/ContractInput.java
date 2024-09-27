package com.school.dinosaur_api.api.representationmodel.input;

import com.school.dinosaur_api.domain.model.Period;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ContractInput {
    @Valid
    @OneToOne
    private StudentIdInput student;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Period period;

    @NotNull
    private Date input;

    @NotNull
    private Date output;
}
