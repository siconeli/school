package com.school.dinosaur_api.api.representationmodel.output;

import com.school.dinosaur_api.domain.model.Period;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
public class ContractOutput {
    private Long id;

    private Boolean active;

    private LocalDate registerDate;

    private StudentSummary student;

    private Period contractedPeriod;

    private Date dateInput;

    private Date dateOutput;
}
