package services.impl;

import dao.IBankDao;
import dao.IClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojos.Bank;
import services.BaseService;
import services.IBankService;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class BankService extends BaseService<Bank> implements IBankService {

    @Autowired
    IBankDao bankDao;

    @Autowired
    IClientDao clientDao;

    @Override
    public List<Bank> getBankAccounts() {
        return bankDao.getBankAccounts();
    }

    @Override
    public Bank getBankAccountInfoByClientID(Long clientID) {
        Bank bank = bankDao.getBankAccountInfoByClientID(clientID);
        if (bank.equals(null)) {
            return bankDao.add(new Bank("AAAAAAAAAABBBBCCCCCCCC", clientDao.get(clientID), 0, new ArrayList<>()));
        }
        return bank;
    }

    @Override
    public Double getBalanceByClientID(Long clientID) {
        return bankDao.getBalanceByClientID(clientID);
    }

    @Override
    public Bank getBankAccountByCreditCardNumber(long cardNumber) {
        return bankDao.getBankAccountByCreditCardNumber(cardNumber);
    }

}
