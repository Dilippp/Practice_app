package com.nineleaps.banking.controller;

import com.nineleaps.banking.dto.TransactionDto;
import com.nineleaps.banking.entity.Transaction;
import com.nineleaps.banking.mapper.TransactionMapper;
import com.nineleaps.banking.service.TransactionsService;
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
public class TransactionsController {

    private final TransactionsService transactionsService;
    private final TransactionMapper transactionMapper;

    @GetMapping("/transactions")
    public List<TransactionDto> getTransactions() {
        List<Transaction> allTransactions = transactionsService.getAllTransactions();
        return allTransactions.stream().map(transactionMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/transactions/{id}")
    public TransactionDto find(@PathVariable("id") Integer id) {
        return transactionMapper.toDto(transactionsService.getTransactionById(id));
    }

    @GetMapping("/accounts/{id}/transactions")
    public List<TransactionDto> findByAccountId(@PathVariable("id") Integer id) {
        return transactionsService.findByAccountId(id).stream()
                .map(transactionMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/accounts/{accountId}/transactions/{transactionId}")
    public TransactionDto findByAccountIdAndTransactionId(@PathVariable("accountId") Integer accountId,
                            @PathVariable("transactionId") Integer transactionId) {
        return transactionMapper.toDto(transactionsService
                .findByAccountIdAndTransactionId(accountId, transactionId));
    }

    @PostMapping("/transactions")
    public TransactionDto create(@RequestBody TransactionDto transactionDto) {
        return transactionMapper.toDto(transactionsService.createOrUpdateTransaction(transactionDto));
    }

    @PutMapping("/transactions/{id}")
    public TransactionDto update(@RequestBody TransactionDto transactionDto, @PathVariable("id") Integer id) {
        transactionDto.setId(id);
        return transactionMapper.toDto(transactionsService.createOrUpdateTransaction(transactionDto));
    }

    @DeleteMapping(value = "/transactions/{id}")
    public void delete(@PathVariable("id") Integer id) {
        transactionsService.deleteTransaction(id);
    }
}
