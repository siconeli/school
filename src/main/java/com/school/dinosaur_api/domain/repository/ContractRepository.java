package com.school.dinosaur_api.domain.repository;

import com.school.dinosaur_api.domain.model.Contract;
import com.school.dinosaur_api.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    boolean existsByStudent(Student student);
    Optional<Contract> findByStudentId(Long studentId);
}



