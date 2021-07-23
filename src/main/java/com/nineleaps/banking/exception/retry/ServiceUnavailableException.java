package com.nineleaps.banking.exception.retry;

public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException() {
        super();
    }

    public ServiceUnavailableException(String message) {
        super(message);
    }
}
