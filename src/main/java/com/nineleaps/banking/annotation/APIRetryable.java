package com.nineleaps.banking.annotation;

import com.nineleaps.banking.exception.retry.EmptyContentException;
import com.nineleaps.banking.exception.retry.ServiceUnavailableException;
import com.nineleaps.banking.exception.retry.WrongResponseFormatException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.SocketException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

@Retention(RetentionPolicy.RUNTIME)
@Retryable(
        value = {
            SocketException.class,
            EmptyContentException.class,
            ServiceUnavailableException.class,
            WrongResponseFormatException.class,
            RuntimeException.class
        },
        maxAttemptsExpression = "${api.client.retry.max.attempts:3}",
        backoff =
                @Backoff(
                        delayExpression = "${api.client.retry.delay-ms:3000}",
                        multiplierExpression = "${api.client.retry.delay-multiplier:2}"),
        listeners = {"retryLoggingListener"})
public @interface APIRetryable {}
