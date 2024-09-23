package com.school.dinosaur_api.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Form {
    @Id
    private Long id;

//    private Student student;

    private Boolean specialCare; //Cuidado especial

    private Boolean allergic; //Alérgico

    private Boolean intolerantFood;  //Intolerante a algum alimento

    private Boolean sufferedFalls; //Sofreu quedas

    private Boolean reflux; //Refluxo

    private Boolean seizures; //Convulsões

    private Boolean continuousMedicine; //Remédio de uso contínuo

    private String observations;
}
