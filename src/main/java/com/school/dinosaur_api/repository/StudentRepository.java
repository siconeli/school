package com.school.dinosaur_api.repository;

import com.school.dinosaur_api.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
