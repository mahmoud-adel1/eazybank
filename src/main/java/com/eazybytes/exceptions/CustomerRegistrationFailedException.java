package com.eazybytes.exceptions;

public class CustomerRegistrationFailedException extends RuntimeException {

    public CustomerRegistrationFailedException(String message) {
        super(message);
    }
}
