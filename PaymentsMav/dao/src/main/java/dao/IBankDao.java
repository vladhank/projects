package dao;

import pojos.Bank;
import pojos.CreditCard;

import java.util.List;

public interface IBankDao extends Dao<Bank> {

    List<Bank> getBankAccounts();

    Bank getBankAccountInfoByClientID(Long clientID);

    Double getBalanceByClientID(Long clientID);

    Bank getBankAccountByCreditCardNumber(long CreditCardNumber);
}
