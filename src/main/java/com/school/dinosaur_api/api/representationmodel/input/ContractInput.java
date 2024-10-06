package com.school.dinosaur_api.api.representationmodel.input;

import com.school.dinosaur_api.domain.model.Period;
import com.school.dinosaur_api.domain.validation.ValidationGroups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.groups.Default;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class ContractInput {
//    @Valid
//    @NotNull
//    private StudentIdInput student;

    @Positive(groups = {Default.class, ValidationGroups.UpdateValidation.class})
    @NotNull
    private BigDecimal value;

    @NotNull
    private Period contractedPeriod;

    @NotNull
    private Date dateInput;

    @NotNull
    private Date dateOutput;
}
