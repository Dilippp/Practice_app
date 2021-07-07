package com.nineleaps.banking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public final class ExceptionResponse {

    private int status;
    private String error;
}
