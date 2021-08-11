package com.nineleaps.banking.service;

import com.nineleaps.banking.annotation.APIRetryable;
import com.nineleaps.banking.dto.AccountDto;
import com.nineleaps.banking.entity.Account;
import com.nineleaps.banking.exception.ResourceNotFoundException;
import com.nineleaps.banking.mapper.AccountMapper;
import com.nineleaps.banking.repository.AccountsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountsService {

    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;

    @Cacheable(value = "accounts")
    public List<Account> getAllAccounts() {
        log.info("Fetching all accounts");
        return accountsRepository.findAll();
    }

    @Cacheable(value = "accounts")
    public Page<Account> findAll(Specification<Account> specification, Pageable pageable) {
        log.info("Filtering accounts");
        return accountsRepository.findAll(specification, pageable);
    }

    @Cacheable(value = "account")
    public Account getAccountById(Integer id) {
        return accountsRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account doesn't exit with id: " + id));
    }

    @Transactional
    @Caching(
            evict = {
                @CacheEvict(value = "account", allEntries = true),
                @CacheEvict(value = "accounts", allEntries = true)
            })
    public Account createOrUpdateAccount(AccountDto accountDto) {
        return accountsRepository.save(accountMapper.toEntity(accountDto));
    }

    @Transactional
    @Caching(
            evict = {
                @CacheEvict(value = "account", allEntries = true),
                @CacheEvict(value = "accounts", allEntries = true)
            })
    public void deleteAccount(Integer id) {

        if (getAccountById(id) != null) {
            accountsRepository.deleteById(id);
        }
    }

    @APIRetryable
    public void fetchAccountsFromAnotherService() {
        throw new RuntimeException("Service not available at the moment");
    }
}
