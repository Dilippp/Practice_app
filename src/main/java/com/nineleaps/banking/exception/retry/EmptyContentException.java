package com.nineleaps.banking.exception.retry;

public class EmptyContentException extends RuntimeException {

    public EmptyContentException() {
        super();
    }

    public EmptyContentException(String message) {
        super(message);
    }

    public EmptyContentException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
