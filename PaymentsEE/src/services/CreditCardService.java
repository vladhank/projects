package services;

import entities.CreditCard;

import java.io.Serializable;
import java.util.List;

public interface CreditCardService {
    CreditCard save(CreditCard creditCard);

    CreditCard get(Serializable cardNumber);

    void update(CreditCard creditCard);

    int delete(Serializable cardNumber);

    List<CreditCard> getExpiredCards();

    List<CreditCard> getCardsByID(Serializable clientID);

    double getBalance(long cardNumber);

    boolean sendMoney(double amountOfMoney, long cardNumberSender, long cardNumberReceiver);

    List<CreditCard> getAll();
}
