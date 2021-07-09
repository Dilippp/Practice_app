package com.nineleaps.banking.exception;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@XmlRootElement
@NoArgsConstructor
public final class ExceptionResponse {

    private int status;
    private String error;
}
