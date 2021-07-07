package com.nineleaps.banking.exception;

public class MethodNotAllowedException extends RuntimeException {

    public MethodNotAllowedException() {
        super();
    }

    public MethodNotAllowedException(String message) {
        super(message);
    }

    public MethodNotAllowedException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
