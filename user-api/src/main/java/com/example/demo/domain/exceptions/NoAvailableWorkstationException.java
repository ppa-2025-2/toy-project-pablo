package com.example.demo.domain.exceptions;

public class NoAvailableWorkstationException extends RuntimeException {
    public NoAvailableWorkstationException(String message) {
        super(message);
    }
}
