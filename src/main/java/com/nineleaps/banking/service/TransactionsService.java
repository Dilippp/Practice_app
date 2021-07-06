package com.nineleaps.banking.service;

import com.nineleaps.banking.entity.Transaction;
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

    public List<Transaction> getAllTransactions() {
        log.info("Fetching all transactions");
        return transactionsRepository.findAll();
    }

    public Transaction getTransactionById(Integer id) {
        return transactionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction doesn't exit with id: " + id));
    }

    @Transactional
    public Transaction createOrUpdateTransaction(Transaction transaction) {
        return transactionsRepository.save(transaction);
    }

    @Transactional
    public void deleteTransaction(Integer id) {
    log.info("Deleting transaction for id: {}", id);
        if(getTransactionById(id) != null) {
            transactionsRepository.deleteById(id);
        }
    }
}
