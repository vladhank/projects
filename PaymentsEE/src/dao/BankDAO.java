package dao;

import entities.Bank;

import java.sql.SQLException;
import java.util.List;

public interface BankDAO extends DAO<Bank> {
    Bank getAccountWithSmallBalance()throws SQLException;
    List<Bank> getAll() throws SQLException;
}
