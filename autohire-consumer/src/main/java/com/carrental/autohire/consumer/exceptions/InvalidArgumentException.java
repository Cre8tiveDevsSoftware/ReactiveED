package com.carrental.autohire.consumer.exceptions;

public class InvalidArgumentException extends RuntimeException {
    private String message = null;
    public InvalidArgumentException(String message) {
        super(message);
        this.message = message;
    }
}
