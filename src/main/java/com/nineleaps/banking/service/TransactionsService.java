package com.nineleaps.banking.service;

import com.nineleaps.banking.dto.AccountDto;
import com.nineleaps.banking.dto.TransactionDto;
import com.nineleaps.banking.entity.Account;
import com.nineleaps.banking.entity.Transaction;
import com.nineleaps.banking.mapper.AccountMapper;
import com.nineleaps.banking.mapper.TransactionMapper;
import com.nineleaps.banking.repository.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionsService {

    private final TransactionsRepository transactionsRepository;
    private final AccountsService accountsService;
    private final TransactionMapper transactionMapper;
    private final AccountMapper accountMapper;

    public List<Transaction> getAllTransactions() {
        log.info("Fetching all transactions");
        return transactionsRepository.findAll();
    }

    public Transaction getTransactionById(Integer id) {
        return transactionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction doesn't exit with id: " + id));
    }

    @Transactional
    public Transaction createOrUpdateTransaction(TransactionDto transactionDto) {
        AccountDto accountDto = accountMapper.toDto(accountsService.getAccountById(transactionDto.getAccountDto().getId()));
        transactionDto.setAccountDto(accountDto);
        return transactionsRepository.save(transactionMapper.toEntity(transactionDto));
    }

    @Transactional
    public void deleteTransaction(Integer id) {
    log.info("Deleting transaction for id: {}", id);
        if(getTransactionById(id) != null) {
            transactionsRepository.deleteById(id);
        }
    }

    public List<Transaction> findByAccountId(Integer id) {
        return transactionsRepository.findByAccountId(id);
    }

    public Transaction findByAccountIdAndTransactionId(Integer accountId, Integer transactionId) {
        return transactionsRepository.findByAccountIdAndId(accountId, transactionId);
    }
}
