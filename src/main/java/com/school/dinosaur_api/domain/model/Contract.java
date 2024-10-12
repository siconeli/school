package com.school.dinosaur_api.domain.model;

import com.school.dinosaur_api.domain.enums.Period;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Contract {
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Boolean active;

    private LocalDate registerDate;


    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private Period contractedPeriod;

    private Date dateInput;

    private Date dateOutput;

    @OneToOne
    private Student student;
}
