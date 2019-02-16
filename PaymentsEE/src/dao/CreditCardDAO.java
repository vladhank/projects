package dao;

import entities.CreditCard;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface CreditCardDAO extends DAO<CreditCard>{
    List<CreditCard> getExpiredCards() throws SQLException,ParseException;
    List<CreditCard> getCardsByID(Serializable id) throws SQLException,ParseException;
    double getBalance(long cardNumber) throws SQLException;
    List<CreditCard> getAll() throws SQLException,ParseException;
}
