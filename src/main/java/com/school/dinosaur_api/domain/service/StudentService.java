package com.school.dinosaur_api.domain.service;

import com.school.dinosaur_api.api.helper.IgnoreNullBeanUtilsBean;
import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.exception.ResourceNotFoundException;
import com.school.dinosaur_api.domain.model.Student;
import com.school.dinosaur_api.domain.repository.ContractRepository;
import com.school.dinosaur_api.domain.repository.ResponsibleRepository;
import com.school.dinosaur_api.domain.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public Student findStudent(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentId));
    }

    @Transactional
    public Student createStudent(Student student) {
        if (student.getId() != null) {
            throw new BusinessException("Must not contain the ID in the request body");
        }

        boolean cpfUsed = studentRepository.findByCpf(student.getCpf())
                .filter(s -> !s.equals(student))
                .isPresent();

        if (cpfUsed) {
            throw new BusinessException("CPF " + student.getCpf() + " already in use");
        }

        student.setActive(true);
        student.setRegisterDate(LocalDate.now());

        return studentRepository.save(student);
    }

    @Transactional
    public Student updatePartialStudent(Student student) {
        Student studentDb = this.findStudent(student.getId());

        boolean cpfUsed = studentRepository.findByCpf(student.getCpf())
                .filter(s -> !s.equals(student))
                .isPresent();

        if (cpfUsed) {
            throw new BusinessException("CPF " + student.getCpf() + " already in use");
        }

        if(student.getCpf() != null) {studentDb.setCpf(student.getCpf());}
        if(student.getName() != null) {studentDb.setName(student.getName());}
        if(student.getAge() != null) {studentDb.setAge(student.getAge());}

        return studentRepository.save(studentDb);
    }

    @Transactional
    public void deleteStudent(Long studentId) {
        this.findStudent(studentId);

        studentRepository.deleteById(studentId);
    }
}
