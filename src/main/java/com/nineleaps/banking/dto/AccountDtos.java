package com.nineleaps.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

@XmlRootElement(name = "accountDtos")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDtos {
    private List<AccountDto> accountDto;
}
