package com.evg.drones.rest;

import com.evg.drones.model.Medication;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Dto {
    private Long droneId;
    private List<Medication> medications;
}
