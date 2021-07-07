package com.nineleaps.banking.repository;

import com.nineleaps.banking.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository <Transaction, Integer> {

    List<Transaction> findByAccountId(Integer id);

    Transaction findByAccountIdAndId(Integer accountId, Integer transactionId);
}
