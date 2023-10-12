package com.carrental.autohire.exceptions;

public class InvalidArgumentException extends RuntimeException {
    private String message;
    public InvalidArgumentException(String message) {
        super(message);
        this.message = message;
    }
}
