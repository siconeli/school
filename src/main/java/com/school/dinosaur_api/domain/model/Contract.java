package com.school.dinosaur_api.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Contract {

    @Id
    private Long id;

//    private Student student;

    private Period period;

    private Date input;

    private Date output;
}
