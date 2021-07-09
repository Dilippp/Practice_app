package com.nineleaps.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "transactionDtos")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDtos {
    private List<TransactionDto> transactionDto;
}