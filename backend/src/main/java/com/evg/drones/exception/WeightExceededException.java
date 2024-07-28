package com.evg.drones.exception;

public class WeightExceededException extends RuntimeException {
    public WeightExceededException(String message) {
        super(message);
    }
}
