package com.school.dinosaur_api.domain.repository;

import com.school.dinosaur_api.api.representationmodel.input.StudentInput;
import com.school.dinosaur_api.domain.model.Student;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class StudentRepositoryTest {
    @Autowired
    EntityManager entityManager;

    @Autowired
    StudentRepository studentRepository;

    @Test
    @DisplayName("Should get Student successfully from DB")
    void findByCpfCase1() {
        String cpf = "47872506807";
        StudentInput studentInput = new StudentInput(cpf, "Henrique Siconeli", 27);
        this.createStudent(studentInput);
        Optional<Student> foundStudent = studentRepository.findByCpf(cpf);
        assertThat(foundStudent.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get Student from DB when Student not exists")
    void findByCpfCase2() {
        String cpf = "47872506807";
        Optional<Student> foundStudent = studentRepository.findByCpf(cpf);
        assertThat(foundStudent.isEmpty()).isTrue();
    }

    private Student createStudent(StudentInput studentInput) {
        Student newStudent = new Student(studentInput);
        this.entityManager.persist(newStudent);
        return newStudent;
    }
}