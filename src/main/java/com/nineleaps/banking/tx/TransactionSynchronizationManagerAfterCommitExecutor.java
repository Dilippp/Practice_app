package com.nineleaps.banking.tx;

import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class TransactionSynchronizationManagerAfterCommitExecutor implements AfterCommitExecutor {

    @Override
    public void afterCommit(Runnable execution) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(
                    new TransactionSynchronization() {
                        @Override
                        public void afterCommit() {
                            execution.run();
                        }
                    });
        }
    }
}
