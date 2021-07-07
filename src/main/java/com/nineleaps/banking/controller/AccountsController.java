package com.nineleaps.banking.controller;

import com.nineleaps.banking.dto.AccountDto;
import com.nineleaps.banking.entity.Account;
import com.nineleaps.banking.mapper.AccountMapper;
import com.nineleaps.banking.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsService accountsService;
    private final AccountMapper accountMapper;

    @GetMapping("/accounts")
    public List<AccountDto> getAccounts() {
        List<Account> allAccounts = accountsService.getAllAccounts();
        return allAccounts.stream().map(accountMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/accounts/{id}")
    public AccountDto find(@PathVariable("id") Integer id) {
        return accountMapper.toDto(accountsService.getAccountById(id));
    }

    @PostMapping("/accounts")
    public AccountDto create(@RequestBody AccountDto accountDto) {
        return accountMapper.toDto(accountsService.createOrUpdateAccount(accountDto));
    }

    @PutMapping("/accounts/{id}")
    public AccountDto update(@RequestBody AccountDto accountDto, @PathVariable("id") Integer id) {
        accountDto.setId(id);
        return accountMapper.toDto(accountsService.createOrUpdateAccount(accountDto));
    }

    @DeleteMapping(value = "/accounts/{id}")
    public void delete(@PathVariable("id") Integer id) {
        accountsService.deleteAccount(id);
    }
}
