package com.nineleaps.banking.tx;

public interface AfterCommitExecutor {
    void afterCommit(Runnable execution);
}
