package com.evg.drones.service;

import com.evg.drones.exception.BatteryLowException;
import com.evg.drones.exception.WeightExceededException;
import com.evg.drones.model.Drone;
import com.evg.drones.model.Medication;
import com.evg.drones.model.Order;
import com.evg.drones.dao.DroneRepository;
import com.evg.drones.dao.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private DroneRepository droneRepository;
    @Mock
    private OrderRepository orderRepository;

    private List<Medication> medicationList;

    @BeforeEach
    public void init() {
        medicationList = new ArrayList<>();
        medicationList.add(Medication.builder()
                .weight(150)
                .build());
        medicationList.add(Medication.builder()
                .weight(160)
                .build());
    }

    @Test
    void whenLoadDroneWithBatteryLow_thenBatteryLowExceptionRaised() {
        when(droneRepository.getReferenceById(1L)).thenReturn(Drone.builder()
                        .id(1L)
                        .batteryCapacity(20)
                .build());
        assertThrows(BatteryLowException.class, () ->
                    orderService.loadDrone(1L, medicationList)
                );
    }

    @Test
    void whenLoadDroneWithWeightExceeded_thenWeightExceededExceptionRaised() {
        when(droneRepository.getReferenceById(1L)).thenReturn(Drone.builder()
                .id(1L)
                .batteryCapacity(100)
                .weightLimit(300)
                .build());
        assertThrows(WeightExceededException.class, () ->
                orderService.loadDrone(1L, medicationList)
        );
    }

    @Test
    void getLoadedMedications() {
        when(droneRepository.getReferenceById(1L)).thenReturn(Drone.builder().id(1L).build());
        when(orderRepository.findByDrone(droneRepository.getReferenceById(1L))).thenReturn(
                Optional.ofNullable(Order.builder().medications(medicationList).build())
        );
        List<Medication> medications = orderService.getLoadedMedications(1L);
        assertEquals(medications.size(), 2);
    }
}