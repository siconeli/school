package com.school.dinosaur_api.domain.service;

import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.exception.ResourceNotFoundException;
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

    public Student findStudent(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
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

        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Student student) {
        if (!studentRepository.existsById(student.getId())) {
            throw new ResourceNotFoundException("Student not found with id: " + student.getId());
        }

        boolean cpfUsed = studentRepository.findByCpf(student.getCpf())
                .filter(s -> !s.equals(student))
                .isPresent();

        if (cpfUsed) {
            throw new BusinessException("CPF " + student.getCpf() + " already in use");
        }

        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found with id: " + studentId);
        }

        studentRepository.deleteById(studentId);
    }
}
