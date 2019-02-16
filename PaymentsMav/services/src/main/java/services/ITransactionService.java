package services;

import pojos.Transaction;

import java.util.List;

public interface ITransactionService extends  IService<Transaction>{
    List<Transaction> getTransactions();
    List<Transaction> getAllTransactionsByClientID(Long clientID);
}
