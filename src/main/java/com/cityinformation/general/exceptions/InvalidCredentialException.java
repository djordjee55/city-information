package com.cityinformation.general.exceptions;

public class InvalidCredentialException extends RuntimeException {
    public InvalidCredentialException() {
        super("Failed to authorize the provided credentials.");
    }
}
