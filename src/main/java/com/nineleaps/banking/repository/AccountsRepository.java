package com.nineleaps.banking.repository;

import com.nineleaps.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository
        extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {}
