package com.nineleaps.banking.exception.retry;

public class WrongResponseFormatException extends RuntimeException {

    public WrongResponseFormatException() {
        super();
    }

    public WrongResponseFormatException(String message) {
        super(message);
    }
}
