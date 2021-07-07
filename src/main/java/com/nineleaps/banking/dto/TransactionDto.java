package com.nineleaps.banking.dto;

import com.nineleaps.banking.entity.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDto {

    private Integer id;
    private String type;
    private Double amount;
    private String description;
    private AccountDto accountDto;
}
