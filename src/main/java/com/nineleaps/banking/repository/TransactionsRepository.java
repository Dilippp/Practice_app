package com.nineleaps.banking.repository;

import com.nineleaps.banking.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository <Transaction, Integer> {
}
