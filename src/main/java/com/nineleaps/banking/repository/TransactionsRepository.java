package com.nineleaps.banking.repository;

import com.nineleaps.banking.entity.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByAccountId(Integer id);

    Transaction findByAccountIdAndId(Integer accountId, Integer transactionId);
}
