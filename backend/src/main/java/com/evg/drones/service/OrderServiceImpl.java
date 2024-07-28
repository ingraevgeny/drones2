package com.evg.drones.service;

import com.evg.drones.dao.DroneRepository;
import com.evg.drones.dao.OrderRepository;
import com.evg.drones.exception.BatteryLowException;
import com.evg.drones.exception.WeightExceededException;
import com.evg.drones.model.Drone;
import com.evg.drones.model.Medication;
import com.evg.drones.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DroneRepository droneRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Order loadDrone(Long droneId, List<Medication> items) {
        Drone drone = droneRepository.getReferenceById(droneId);
        if(drone.getBatteryCapacity() < 25) {
            throw new BatteryLowException("the drone cannot be loaded, battery low");
        }
        int itemsWeight = items.stream()
                .mapToInt(Medication::getWeight)
                .sum();
        if(itemsWeight > drone.getWeightLimit()) {
            throw new WeightExceededException("the drone cannot be loaded, the weight of the medications exceeds the drone's limit");
        }
        return orderRepository.save(Order.builder()
                .drone(drone)
                .medications(items).build());
    }

    @Override
    public List<Medication> getLoadedMedications(Long droneId) {
        return orderRepository.findByDrone(
                        droneRepository.getReferenceById(droneId))
                .orElseThrow(() -> new RuntimeException(String.format("Drone: %d not found", droneId)))
                .getMedications();
    }
}
