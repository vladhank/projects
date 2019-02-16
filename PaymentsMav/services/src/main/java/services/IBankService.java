package services;

import pojos.Bank;

import java.util.List;

public interface IBankService extends IService<Bank> {

    List<Bank> getBankAccounts();

    Bank getBankAccountInfoByClientID(Long clientID);

    Double getBalanceByClientID(Long clientID);

    Bank getBankAccountByCreditCardNumber(long cardNumber);
}
