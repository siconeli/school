package com.school.dinosaur_api.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class StudentRepositoryTest {

    @Test
    void findByCpf() {
    }
}