package com.evg.drones.service;

import com.evg.drones.model.Drone;

import java.util.List;

public interface DroneService {
    Integer getDroneBatteryLevel(Long droneId);
    List<Drone> getAvailableDrones();
    Drone createDrone(Drone drone);

}
