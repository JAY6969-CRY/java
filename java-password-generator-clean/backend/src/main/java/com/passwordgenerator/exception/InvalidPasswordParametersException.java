package com.passwordgenerator.exception;

public class InvalidPasswordParametersException extends RuntimeException {
    public InvalidPasswordParametersException(String message) {
        super(message);
    }

    public InvalidPasswordParametersException(String message, Throwable cause) {
        super(message, cause);
    }
}
