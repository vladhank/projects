package services.impl;

import dao.IBankDao;
import dao.ICreditCardDao;
import dao.ITransactionDao;
import enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojos.Bank;
import pojos.CreditCard;
import pojos.Transaction;
import services.BaseService;
import services.IBankService;
import services.ICreditCardService;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CreditCardService extends BaseService<CreditCard> implements ICreditCardService {

    @Autowired
    ICreditCardDao creditCardDao;

    @Autowired
    IBankDao bankDao;

    @Autowired
    ITransactionDao transactionDao;


    public List<CreditCard> getExpiredCards() {
        return creditCardDao.getExpiredCards();
    }

    public List<CreditCard> getAllClientCards(Serializable clientId) {
        return creditCardDao.getAllClientCards(clientId);
    }

    public List<CreditCard> getCreditCards() {
        return creditCardDao.getCreditCards();
    }

    public List<Transaction> getCardTransactions(long cardNumber) {
        return creditCardDao.getCardTransactions(cardNumber);
    }

    public CreditCard getCreditCardByClientID(Long clientID) {
        return creditCardDao.getCreditCardByClientID(clientID);
    }

    public boolean sendMoney(double amountOfMoney, Long senderClientID, long cardNumberReceiver) {
        double currentBalance = 0;
        CreditCard creditCardSender = creditCardDao.getCreditCardByClientID(senderClientID);
        Bank bankAccountSender = bankDao.getBankAccountInfoByClientID(senderClientID);
        Bank bankAccountReceiver = bankDao.getBankAccountByCreditCardNumber(cardNumberReceiver);
        currentBalance = creditCardDao.getBalance(creditCardSender.getCardNumber());
        if (amountOfMoney <= currentBalance && !creditCardSender.getStatus().equals("DISABLED")) {
            bankAccountReceiver.setBalance(bankAccountReceiver.getBalance() + amountOfMoney);
            bankDao.update(bankAccountReceiver);
            bankAccountSender.setBalance(bankAccountSender.getBalance() - amountOfMoney);
            bankDao.update(bankAccountSender);
//            transactionDao.add(new Transaction(creditCardSender.getCardNumber(), bankAccountSender.getAccountID(), "WITHDRAW", amountOfMoney));
            transactionDao.add(new Transaction(creditCardSender.getCardNumber(), TransactionType.WITHDRAW, amountOfMoney, LocalDateTime.now(), creditCardSender));
//            transactionDao.add(new Transaction(cardNumberReceiver, bankAccountReceiver.getAccountID(), "DEPOSIT", amountOfMoney));
            transactionDao.add(new Transaction(cardNumberReceiver, TransactionType.DEPOSIT,amountOfMoney,LocalDateTime.now(),creditCardDao.get(cardNumberReceiver)));
            return true;
        }
        return false;
    }
        public double getBalance ( long cardNumber){
            return creditCardDao.getBalance(cardNumber);
        }

}
