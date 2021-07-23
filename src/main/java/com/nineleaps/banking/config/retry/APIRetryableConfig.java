package com.nineleaps.banking.config.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.listener.RetryListenerSupport;

@Configuration
@Slf4j
@EnableRetry
public class APIRetryableConfig {

    /** Logging listener used by {@link com.nineleaps.banking.annotation.APIRetryable} */
    @Bean
    public RetryListenerSupport retryLoggingListener() {

        return new RetryListenerSupport() {
            @Override
            public <T, E extends Throwable> void onError(
                    RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
                log.warn(
                        "Retryable method \"{}\" threw {}th exception: \"{}\"",
                        context.getAttribute("context.name"),
                        context.getRetryCount(),
                        throwable.getMessage());
            }
        };
    }
}
