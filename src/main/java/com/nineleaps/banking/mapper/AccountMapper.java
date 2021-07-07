package com.nineleaps.banking.mapper;

import com.nineleaps.banking.dto.AccountDto;
import com.nineleaps.banking.entity.Account;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccountMapper extends ModelMapper<Account, AccountDto> {
}
