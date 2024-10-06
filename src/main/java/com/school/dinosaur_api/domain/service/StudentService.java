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
    private final IgnoreNullBeanUtilsBean ignoreNullBeanUtilsBean;

    public Student findStudent(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentId));
    }

    @Transactional
    public Student createStudent(Student studentDto) {
        if (studentDto.getId() != null) {
            throw new BusinessException("Must not contain the ID in the request body");
        }

        boolean cpfUsed = studentRepository.findByCpf(studentDto.getCpf())
                .filter(s -> !s.equals(studentDto))
                .isPresent();

        if (cpfUsed) {
            throw new BusinessException("CPF " + studentDto.getCpf() + " already in use");
        }

        studentDto.setActive(true);
        studentDto.setRegisterDate(LocalDate.now());

        return studentRepository.save(studentDto);
    }

    @Transactional
    public Student updatePartialStudent(Student studentDto) {
        Student student = this.findStudent(studentDto.getId());

        boolean cpfUsed = studentRepository.findByCpf(studentDto.getCpf())
                .filter(s -> !s.equals(studentDto))
                .isPresent();

        if (cpfUsed) {
            throw new BusinessException("CPF " + studentDto.getCpf() + " already in use");
        }

        if(studentDto.getCpf() != null) {student.setCpf(studentDto.getCpf());}
        if(studentDto.getName() != null) {student.setName(studentDto.getName());}
        if(studentDto.getAge() != null) {student.setAge(studentDto.getAge());}

        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long studentId) {
        this.findStudent(studentId);

        studentRepository.deleteById(studentId);
    }
}
