package bankService.dao;

import bankService.model.Transaction;

public interface TransactionDao {
    void addTransaction(Transaction transaction);
}