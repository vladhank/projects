package dao;

import pojos.CreditCard;
import pojos.Transaction;

import java.io.Serializable;
import java.util.List;

public interface ICreditCardDao extends Dao<CreditCard> {

    List<CreditCard> getExpiredCards();

    List<CreditCard> getAllClientCards(Serializable clientId);

    List<CreditCard> getCreditCards();

    List<Transaction> getCardTransactions(long cardNumber);

    CreditCard getCreditCardByClientID(Long clientID);

    double getBalance(long cardNumber);

}
