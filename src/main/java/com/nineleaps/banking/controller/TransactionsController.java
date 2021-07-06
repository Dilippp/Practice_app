package com.nineleaps.banking.controller;

import com.nineleaps.banking.entity.Transaction;
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

@RestController
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionsService transactionsService;

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return transactionsService.getAllTransactions();
    }

    @GetMapping("/transactions/{id}")
    public Transaction find(@PathVariable("id") Integer id) {
        return transactionsService.getTransactionById(id);
    }

    @PostMapping("/transactions")
    public Transaction create(@RequestBody Transaction transaction) {
        return transactionsService.createOrUpdateTransaction(transaction);
    }

    @PutMapping("/transactions/{id}")
    public Transaction update(@RequestBody Transaction transaction, @PathVariable("id") Integer id) {
        transaction.setId(id);
        return transactionsService.createOrUpdateTransaction(transaction);
    }

    @DeleteMapping(value = "/transactions/{id}")
    public void delete(@PathVariable("id") Integer id) {
        transactionsService.deleteTransaction(id);
    }
}
