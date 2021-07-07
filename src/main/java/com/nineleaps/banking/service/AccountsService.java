package com.nineleaps.banking.service;

import com.nineleaps.banking.dto.AccountDto;
import com.nineleaps.banking.entity.Account;
import com.nineleaps.banking.exception.ResourceNotFoundException;
import com.nineleaps.banking.mapper.AccountMapper;
import com.nineleaps.banking.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountsService {

    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;

    public List<Account> getAllAccounts() {
        log.info("Fetching all accounts");
        return accountsRepository.findAll();
    }

    public Account getAccountById(Integer id) {
        return accountsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account doesn't exit with id: " + id));
    }

    @Transactional
    public Account createOrUpdateAccount(AccountDto accountDto) {
        return accountsRepository.save(accountMapper.toEntity(accountDto));
    }

    @Transactional
    public void deleteAccount(Integer id) {

        if(getAccountById(id) != null) {
            accountsRepository.deleteById(id);
        }
    }
}
