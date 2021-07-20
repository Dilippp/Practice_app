package com.nineleaps.banking.service;

import static org.springframework.util.CollectionUtils.isEmpty;

import com.nineleaps.banking.dto.AccountDto;
import com.nineleaps.banking.dto.TransactionDto;
import com.nineleaps.banking.entity.Transaction;
import com.nineleaps.banking.exception.ResourceNotFoundException;
import com.nineleaps.banking.mapper.AccountMapper;
import com.nineleaps.banking.mapper.TransactionMapper;
import com.nineleaps.banking.repository.TransactionsRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionsService {

    private final TransactionsRepository transactionsRepository;
    private final AccountsService accountsService;
    private final TransactionMapper transactionMapper;
    private final AccountMapper accountMapper;

    @Cacheable(value = "transactions")
    public List<Transaction> getAllTransactions() {
        log.info("Fetching all transactions");
        return transactionsRepository.findAll();
    }

    @Cacheable(value = "transaction")
    public Transaction getTransactionById(Integer id) {
        return transactionsRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "Transaction doesn't exit with id: " + id));
    }

    @Transactional
    @Caching(
            evict = {
                @CacheEvict(value = "transaction", allEntries = true),
                @CacheEvict(value = "transactions", allEntries = true)
            })
    public Transaction createOrUpdateTransaction(TransactionDto transactionDto) {
        AccountDto accountDto =
                accountMapper.toDto(
                        accountsService.getAccountById(transactionDto.getAccountDto().getId()));
        transactionDto.setAccountDto(accountDto);
        return transactionsRepository.save(transactionMapper.toEntity(transactionDto));
    }

    @Transactional
    @Caching(
            evict = {
                @CacheEvict(value = "transaction", allEntries = true),
                @CacheEvict(value = "transactions", allEntries = true)
            })
    public void deleteTransaction(Integer id) {
        log.info("Deleting transaction for id: {}", id);
        if (getTransactionById(id) != null) {
            transactionsRepository.deleteById(id);
        }
    }

    @Cacheable(value = "transactions")
    public List<Transaction> findByAccountId(Integer id) {

        List<Transaction> transactions = transactionsRepository.findByAccountId(id);
        if (!isEmpty(transactions)) {
            return transactions;
        }
        throw new ResourceNotFoundException("Transactions not found with accountId: " + id);
    }

    @Cacheable(value = "transaction")
    public Transaction findByAccountIdAndTransactionId(Integer accountId, Integer transactionId) {

        Transaction transaction =
                transactionsRepository.findByAccountIdAndId(accountId, transactionId);
        if (Objects.nonNull(transaction)) {
            return transaction;
        }
        throw new ResourceNotFoundException(
                "Transaction not found with accountId: "
                        + accountId
                        + " and transactionId: "
                        + transactionId);
    }
}
