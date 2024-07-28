package com.evg.drones.rest;

import com.evg.drones.exception.BatteryLowException;
import com.evg.drones.model.Drone;
import com.evg.drones.model.Medication;
import com.evg.drones.model.Order;
import com.evg.drones.service.DroneService;
import com.evg.drones.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
public class DroneController {

    private final OrderService orderService;
    private final DroneService droneService;
    private final ObjectMapper ignoringObjectMapper;

    @PutMapping("/registering")
    public ResponseEntity<String> droneRegistering(@RequestBody Drone drone){
        try {
            return new ResponseEntity<>("Registered Drone: " +
                    ignoringObjectMapper.writeValueAsString(droneService.createDrone(drone)), HttpStatus.OK );
        } catch (Exception exception) {
            return new ResponseEntity<>("Drone was not registered: " + Arrays.toString(exception.getStackTrace()), HttpStatus.OK);
        }
    }

    @GetMapping("/getAvailableDrones")
    public ResponseEntity<String> getAvailableDrones() throws JsonProcessingException {
        List<Drone> availableDrones = droneService.getAvailableDrones();
        return availableDrones.isEmpty() ?
            new ResponseEntity<>("There are no drones available at the moment", HttpStatus.OK) :
            new ResponseEntity<>(ignoringObjectMapper.writeValueAsString(availableDrones), HttpStatus.OK);
    }

    @GetMapping("/getMedications/{droneId}")
    public ResponseEntity<String> getMedications(@PathVariable Long droneId) {
        try {
            List<Medication> medications = orderService.getLoadedMedications(droneId);
            if(!medications.isEmpty()) {
                return new ResponseEntity<>(ignoringObjectMapper.writeValueAsString(medications), HttpStatus.OK);
            }
            throw new BatteryLowException("Medications not found");
        }
        catch(RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (JsonProcessingException e) {
            throw new BatteryLowException("");
        }
    }

    @GetMapping("/getBatteryLevel/{droneId}")
    public ResponseEntity<Integer> getBatteryLevel(@PathVariable Long droneId) {
       return new ResponseEntity<>(droneService.getDroneBatteryLevel(droneId), HttpStatus.OK);
    }

    @PostMapping("/loading")
    public ResponseEntity<String> loadingDrone(@RequestBody Dto dto) throws JsonProcessingException {
        try {
            Order order = orderService.loadDrone(dto.getDroneId(), dto.getMedications());
            return new ResponseEntity<>("Drone loaded: " +
                    ignoringObjectMapper.writeValueAsString(order), HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>("Drone was not loaded: " +
                    ignoringObjectMapper.writeValueAsString(ex.getMessage()) + ignoringObjectMapper.writeValueAsString(ex.getCause()), HttpStatus.OK);
        }
    }
}
