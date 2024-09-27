package com.school.dinosaur_api.api.assembler;

import com.school.dinosaur_api.api.representationmodel.output.StudentOutput;
import com.school.dinosaur_api.domain.model.Student;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class StudentAssembler {
    private final ModelMapper modelMapper;

//    public Student toEntity (StudentInput studentInput) {
//        return modelMapper.map(studentInput, Student.class);
//    }

    public StudentOutput toRepresentationModel (Student student) {
        return modelMapper.map(student, StudentOutput.class);
    }

    public List<StudentOutput> toCollectionRepresentationModel (List<Student> studentList) {
        return studentList
                .stream()
                .map(this::toRepresentationModel)
                .toList();
    }
}
