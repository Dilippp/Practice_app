package com.nineleaps.banking.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlRootElement(name = "accountDtos")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDtos {
    private List<AccountDto> accountDto;
}
