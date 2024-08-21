package com.evg.drones.validation;

import com.evg.drones.model.Drone;
import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintViolationException;
import com.evg.drones.dao.DroneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ValidateDroneTest {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void whenInputInvalid_throwException() {
        Drone invalidDrone = Drone.builder()
                .weightLimit(501)
                .build();

        assertThrows(ConstraintViolationException.class, () -> {
            droneRepository.save(invalidDrone);
            entityManager.flush();
        });
    }

}
