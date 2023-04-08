package com.aksa.weather.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public DataNotFoundException(String message) {
        super(message);
    }
    public DataNotFoundException(Throwable cause) {
        super(cause);
    }
}
