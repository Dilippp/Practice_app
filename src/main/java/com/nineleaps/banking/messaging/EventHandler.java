package com.nineleaps.banking.messaging;

public interface EventHandler<T> extends MessageHandler<T> {

    @Override
    default Type handlerType() {
        return Type.event;
    }
}
