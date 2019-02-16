package dao;

import pojos.Transaction;

import java.util.List;

public interface ITransactionDao extends Dao<Transaction> {

    List<Transaction> getTransactions();

    List<Transaction> getAllTransactionsByClientID(Long clientID);

}
