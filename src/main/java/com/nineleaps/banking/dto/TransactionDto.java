package com.nineleaps.banking.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel(description = "All the details related to Transaction")
public class TransactionDto {

    @ApiModelProperty(notes = "The transaction id", hidden = true)
    private Integer id;

    @NotBlank(message="Transaction type cannot be empty.")
    @ApiModelProperty(notes = "Transaction type")
    private String type;

    @NotNull(message = "Amount can't be null.")
    @Range(min = 1, message = "Amount must be greater than zero")
    @ApiModelProperty(notes = "Transaction amount")
    private Double amount;

    @ApiModelProperty(notes = "Transaction description")
    private String description;

    @ApiModelProperty(notes = "Associated account in transaction")
    @Valid
    private AccountDto accountDto;
}
