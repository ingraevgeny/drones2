package com.evg.drones.model;

import com.evg.drones.dict.Model;
import com.evg.drones.dict.State;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Data
@NoArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Drone {
    @Id
    @GenericGenerator(
            name = "customers-sequence-generator",
            type = org.hibernate.id.enhanced.SequenceStyleGenerator.class,
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "drones_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "10"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            })
    @GeneratedValue(generator = "customers-sequence-generator")
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
