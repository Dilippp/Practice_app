package com.nineleaps.banking.messaging;

public interface ResponseHandler<T> extends MessageHandler<T> {

    @Override
    default Type handlerType() {
        return Type.response;
    }
}
