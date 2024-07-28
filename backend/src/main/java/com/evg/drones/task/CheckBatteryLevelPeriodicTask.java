package com.evg.drones.task;

import com.evg.drones.dao.DroneRepository;
import com.evg.drones.dao.LogBatteryLevelRepository;
import com.evg.drones.model.LogBatteryLevel;
import com.evg.drones.service.DroneService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@Slf4j
@AllArgsConstructor
@EnableAsync
public class CheckBatteryLevelPeriodicTask {

    private final DroneRepository droneRepository;
    private final LogBatteryLevelRepository logBatteryLevelRepository;
    private final DroneService droneService;

    @Scheduled(fixedRate = 60000)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Async
    public void performTask() {

        droneRepository.findAll().forEach(d ->
            logBatteryLevelRepository.save(LogBatteryLevel.builder()
                        .drone(d)
                        .state(d.getState())
                        .batteryLevel(droneService.getDroneBatteryLevel(d.getId()))
                        .currentTime(LocalDateTime.now())
                    .build())
        );

        log.info("Log battery level task performed at {}", LocalDateTime.now());
    }
}
