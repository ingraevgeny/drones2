package com.evg.drones.dao;

import com.evg.drones.dict.State;
import com.evg.drones.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    Optional<List<Drone>> findDronesByState(State state);
}
