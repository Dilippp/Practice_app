package com.nineleaps.banking.mapper;

import com.nineleaps.banking.dto.TransactionDto;
import com.nineleaps.banking.entity.Transaction;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TransactionMapper extends ModelMapper<Transaction, TransactionDto> {

    @Override
    @Mapping(target = "accountDto.id", source = "account.id")
    @Mapping(target = "accountDto.name", source = "account.name")
    @Mapping(target = "accountDto.type", source = "account.type")
    @Mapping(target = "accountDto.openDate", source = "account.openDate")
    TransactionDto toDto(Transaction transaction);

    @Override
    @Mapping(source = "accountDto.id", target = "account.id")
    @Mapping(source = "accountDto.name", target = "account.name")
    @Mapping(source = "accountDto.type", target = "account.type")
    @Mapping(source = "accountDto.openDate", target = "account.openDate")
    Transaction toEntity(TransactionDto transactionDto);
}
