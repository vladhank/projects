package services;

import pojos.CreditCard;
import pojos.Transaction;

import java.io.Serializable;
import java.util.List;

public interface ICreditCardService extends IService<CreditCard> {

    List<CreditCard> getExpiredCards();

    List<CreditCard> getAllClientCards(Serializable clientId);

    List<CreditCard> getCreditCards();

    List<Transaction> getCardTransactions(long cardNumber);

    CreditCard getCreditCardByClientID(Long clientID);

    boolean sendMoney(double amountOfMoney, Long senderClientID, long cardNumberReceiver);

    double getBalance(long cardNumber);
}
