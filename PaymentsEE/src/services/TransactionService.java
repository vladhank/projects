package services;

import entities.Transaction;

import java.io.Serializable;
import java.util.List;

public interface TransactionService {
    Transaction save(Transaction transaction);

    Transaction get(Serializable id);

    void update(Transaction transaction);

    int delete(Serializable id);

    List<Transaction> getDayLimitWithdrawTransactions();

    List<Transaction> getAllByClientID(Serializable id);

    List<Transaction> getAll();
}
