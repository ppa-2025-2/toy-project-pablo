package com.example.demo.domain.exceptions;

public class WorkstationOccupiedException extends RuntimeException {
    public WorkstationOccupiedException(String message) {
        super(message);
    }
}
