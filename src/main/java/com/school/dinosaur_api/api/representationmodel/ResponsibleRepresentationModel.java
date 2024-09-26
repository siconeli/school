package com.school.dinosaur_api.api.representationmodel;

import com.school.dinosaur_api.domain.model.Kinship;
import com.school.dinosaur_api.domain.model.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsibleRepresentationModel {
    private Long id;
    private String studentName;
    private String cpf;
    private String name;
    private String tel;
    private String address;
    private String profession;
    private Boolean authorized;
    private Kinship kinship;
}
