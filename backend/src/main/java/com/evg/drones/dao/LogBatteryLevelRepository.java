package com.evg.drones.dao;

import com.evg.drones.model.LogBatteryLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogBatteryLevelRepository extends JpaRepository<LogBatteryLevel, Long> {
}
