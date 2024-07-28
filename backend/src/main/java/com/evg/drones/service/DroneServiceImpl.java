package com.evg.drones.service;

import com.evg.drones.dao.DroneRepository;
import com.evg.drones.dict.State;
import com.evg.drones.model.Drone;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    @Override
    public Integer getDroneBatteryLevel(Long droneId) {
        return droneRepository.getReferenceById(droneId).getBatteryCapacity();
    }

    @Override
    public List<Drone> getAvailableDrones() {
        return droneRepository.findAll().stream()
                .filter(d -> d.getState().equals(State.IDLE) && d.getBatteryCapacity() > 25)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Drone createDrone(Drone drone) {
        return droneRepository.save(drone);
    }
}
