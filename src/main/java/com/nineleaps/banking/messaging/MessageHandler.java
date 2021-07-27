package com.nineleaps.banking.messaging;

public interface MessageHandler<T> {

    enum Type {
        response,
        event,
        notification
    }

    Type handlerType();

    void handle(T result) throws Exception;
}
