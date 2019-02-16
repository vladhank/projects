package dao;

import entities.Transaction;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface TransactionDAO extends DAO<Transaction>{
    List<Transaction> getDayLimitWithdrawTransaction() throws SQLException;
    List<Transaction> getAllByClientID(Serializable id) throws SQLException;
    List<Transaction> getAll() throws SQLException;
}
