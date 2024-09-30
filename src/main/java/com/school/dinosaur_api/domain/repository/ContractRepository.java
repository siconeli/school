package com.school.dinosaur_api.domain.repository;

import com.school.dinosaur_api.domain.model.Contract;
import com.school.dinosaur_api.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    boolean existsByStudent(Student student); // Query method
}
