package com.nineleaps.banking.controller;

import com.nineleaps.banking.entity.Account;
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

@RestController
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsService accountsService;

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountsService.getAllAccounts();
    }

    @GetMapping("/accounts/{id}")
    public Account find(@PathVariable("id") Integer id) {
        return accountsService.getAccountById(id);
    }

    @PostMapping("/accounts")
    public Account create(@RequestBody Account account) {
        return accountsService.createOrUpdateAccount(account);
    }

    @PutMapping("/accounts/{id}")
    public Account update(@RequestBody Account account, @PathVariable("id") Integer id) {
        account.setId(id);
        return accountsService.createOrUpdateAccount(account);
    }

    @DeleteMapping(value = "/accounts/{id}")
    public void delete(@PathVariable("id") Integer id) {
        accountsService.deleteAccount(id);
    }
}
