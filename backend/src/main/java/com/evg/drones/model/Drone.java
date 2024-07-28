package com.evg.drones.model;

import com.evg.drones.dict.Model;
import com.evg.drones.dict.State;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(length = 100)
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private Model model;
    @Max(500)
    private Integer weightLimit;
    private Integer batteryCapacity;
    @Enumerated(EnumType.STRING)
    private State state;
}
