package com.evg.drones.service;

import com.evg.drones.model.Medication;
import com.evg.drones.model.Order;

import java.util.List;

public interface OrderService {
    public Order loadDrone(Long droneId, List<Medication> items);
    public List<Medication> getLoadedMedications(Long droneId);
}
