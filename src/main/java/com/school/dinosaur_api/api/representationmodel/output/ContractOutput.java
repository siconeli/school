package com.school.dinosaur_api.api.representationmodel.output;

import com.school.dinosaur_api.domain.model.Period;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ContractOutput {
    private Long id;

    private StudentSummary student;

    private Period period;

    private Date input;

    private Date output;
}
