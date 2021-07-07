package com.nineleaps.banking.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AccountDto {

    private Integer id;
    private String name;
    private String type;
    private LocalDate openDate;
}
