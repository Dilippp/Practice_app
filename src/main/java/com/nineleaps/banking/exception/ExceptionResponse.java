package com.nineleaps.banking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Setter
@Getter
@AllArgsConstructor
@XmlRootElement
@NoArgsConstructor
public final class ExceptionResponse {

    private int status;
    private String error;
}
