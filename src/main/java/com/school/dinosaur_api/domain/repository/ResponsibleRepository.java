package com.school.dinosaur_api.domain.repository;

import com.school.dinosaur_api.domain.model.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, Long> {
    Optional<Responsible> findByCpf(String cpf);
}
