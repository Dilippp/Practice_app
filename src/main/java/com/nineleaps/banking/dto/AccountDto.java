package com.nineleaps.banking.dto;

import com.nineleaps.banking.utils.LocalDateAdapter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "accountDto", description = "All the details related to Account")
@XmlRootElement(name = "accountDto") // to support xml based req and res
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountDto {

    @ApiModelProperty(notes = "The account id", hidden = true)
    private Integer id;

    @NotBlank(message = "Account name cannot be empty.")
    @ApiModelProperty(notes = "Account name")
    private String name;

    @NotBlank(message = "Account type cannot be empty.")
    @ApiModelProperty(notes = "Account type")
    private String type;

    // @NotBlank(message="Account opening date cannot be null")
    @ApiModelProperty(notes = "Account opening date")
    @Valid
    @XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
    private LocalDate openDate;
}
