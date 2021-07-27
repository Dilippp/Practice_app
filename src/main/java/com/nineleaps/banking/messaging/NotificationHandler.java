package com.nineleaps.banking.messaging;

public interface NotificationHandler<T> extends MessageHandler<T> {

    @Override
    default Type handlerType() {
        return Type.notification;
    }
}
