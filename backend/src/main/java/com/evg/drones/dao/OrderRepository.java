package com.evg.drones.dao;

import com.evg.drones.model.Drone;
import com.evg.drones.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByDrone(Drone drone);
}
