package com.evg.drones.validation;

import com.evg.drones.model.Medication;
import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintViolationException;
import com.evg.drones.dao.MedicationRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ValidateMedicationTest {
    @Autowired
    private MedicationRepository validateMedicationRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void whenInputInvalid_throwException() {
        Medication invalidMedication = Medication.builder()
                .name("asd$%$&")
                .build();

        assertThrows(ConstraintViolationException.class, () -> {
            validateMedicationRepository.save(invalidMedication);
            entityManager.flush();
        });
    }
}
