package com.bci.exception;

public class InvalidUUIDFormatException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public InvalidUUIDFormatException(String message) {
        super(message);
    }
}

