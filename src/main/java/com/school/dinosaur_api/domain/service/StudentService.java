package com.school.dinosaur_api.domain.service;

import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.model.Student;
import com.school.dinosaur_api.domain.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Transactional
    public Student saveStudent(Student student) {
        boolean cpfUsed = studentRepository.findByCpf(student.getCpf())
                .map(s -> !s.equals(student))
                .isPresent();

        if (cpfUsed) {
            throw new BusinessException("CPF already in use");
        }

        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
