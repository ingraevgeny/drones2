package com.evg.drones.model;

import com.evg.drones.dict.State;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class LogBatteryLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @ManyToOne
    private Drone drone;
    @Enumerated(EnumType.STRING)
    private State state;
    private Integer batteryLevel;
    private LocalDateTime currentTime;
}
