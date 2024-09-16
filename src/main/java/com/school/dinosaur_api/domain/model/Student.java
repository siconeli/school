package com.school.dinosaur_api.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends Person{

    private Long id;
    private Integer age;
}
