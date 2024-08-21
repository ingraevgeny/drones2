package com.evg.drones.service;

import com.evg.drones.model.Drone;
import com.evg.drones.dict.State;
import com.evg.drones.dao.DroneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DroneServiceImplTest {

    @InjectMocks
    private DroneServiceImpl droneService;

    @Mock
    private DroneRepository droneRepository;

    @Test
    void getDroneBatteryLevel() {
        when(droneRepository.getReferenceById(1L)).thenReturn(Drone.builder()
                .batteryCapacity(60)
                .build()
        );
        int batteryCapacity = droneService.getDroneBatteryLevel(1L);
        assertEquals(batteryCapacity, 60);
    }

    @Test
    void whenAskAvailableDrones_thenGetWithIdleStateAndGoodCapacity() {
        List<Drone> allDrones = new ArrayList<>();
        allDrones.add(Drone.builder()
                    .serialNumber("drone1")
                    .state(State.IDLE)
                    .batteryCapacity(20)
                .build());
        allDrones.add(Drone.builder()
                    .serialNumber("drone2")
                    .state(State.IDLE)
                    .batteryCapacity(30)
                .build());
        when(droneRepository.findAll()).thenReturn(allDrones);
        List<Drone> availableDrones = droneService.getAvailableDrones();
        assertEquals(availableDrones.size(), 1);
    }
}