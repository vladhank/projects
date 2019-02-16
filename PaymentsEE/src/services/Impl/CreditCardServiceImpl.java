package services.Impl;

import dao.BankDAO;
import dao.CreditCardDAO;
import dao.Impl.BankDAOImpl;
import dao.Impl.CreditCardDAOImpl;
import dao.Impl.TransactionDAOImpl;
import dao.TransactionDAO;
import entities.Bank;
import entities.CreditCard;
import entities.Transaction;
import org.apache.log4j.Logger;
import services.CreditCardService;
import services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class CreditCardServiceImpl extends AbstractService implements CreditCardService {
    private CreditCardDAO creditCardDAO = CreditCardDAOImpl.getInstance();
    private BankDAO bankDAO = BankDAOImpl.getInstance();
    private TransactionDAO transactionDAO = TransactionDAOImpl.getInstance();
    private static volatile CreditCardService INSTANCE = null;
    public final static Logger logger = Logger.getLogger(CreditCardServiceImpl.class);

    @Override
    public CreditCard save(CreditCard creditCard) {
        try {
            startTransaction();
            creditCard = creditCardDAO.save(creditCard);
            commit();
        } catch (SQLException e) {
            rollback();
            logger.error("Error saving new credit card " + creditCard + e);
            throw new ServiceException("Error saving new credit card " + creditCard + e);
        }
        return creditCard;
    }

    @Override
    public CreditCard get(Serializable cardNumber) {
        try {
            return creditCardDAO.get(cardNumber);
        } catch (Throwable e) {
            logger.error("Error while getting info about credit card " + cardNumber + " reason: " + e);
            throw new ServiceException("Error while getting info about credit card " + cardNumber + " reason: " + e);
        }

    }

    @Override
    public List<CreditCard> getCardsByID(Serializable clientID) {
        try {
            return creditCardDAO.getCardsByID(clientID);
        } catch (Throwable e) {
            logger.error("Error while getting info about credit card " + clientID + " reason: " + e);
            throw new ServiceException("Error while getting info about credit card " + clientID + " reason: " + e);
        }

    }

    @Override
    public void update(CreditCard creditCard) {
        try {
            startTransaction();
            creditCardDAO.update(creditCard);
            commit();
        } catch (SQLException e) {
            rollback();
            logger.error("Error updating credit card " + creditCard + " reason: " + e);
            throw new ServiceException("Error updating credit card " + creditCard + " reason: " + e);
        }
    }

    @Override
    public int delete(Serializable cardNumber) {
        try {
            return creditCardDAO.delete(cardNumber);
        } catch (SQLException e) {
            logger.error("Error deleting credit card " + cardNumber + " reason: " + e);
            throw new ServiceException("Error deleting credit card " + cardNumber + " reason: " + e);
        }
    }

    @Override
    public List<CreditCard> getExpiredCards() {
        try {
            startTransaction();
            List<CreditCard> list = creditCardDAO.getExpiredCards();
            commit();
            return list;
        } catch (SQLException | NullPointerException | ParseException e) {
            rollback();
            logger.error("Error while getting expired cards " + " reason: " + e);
            throw new ServiceException("Error while getting expired cards " + " reason: " + e);

        }
    }

    @Override
    public List<CreditCard> getAll() {
        try {
            startTransaction();
            List<CreditCard> list = creditCardDAO.getAll();
            commit();
            return list;
        } catch (SQLException | NullPointerException | ParseException e) {
            rollback();
            logger.error("Error while getting list of cards " + " reason: " + e);
            throw new ServiceException("Error while getting list of cards " + " reason: " + e);

        }
    }

    @Override
    public double getBalance(long cardNumber) {
        double currentBalance = 0;
        try {
            currentBalance = creditCardDAO.getBalance(cardNumber);
        } catch (SQLException e) {
            logger.error("Error while getting balance from " + cardNumber + " reason: " + e);
            throw new ServiceException("Error while getting balance from " + cardNumber + " reason: " + e);
        }
        return currentBalance;
    }

    @Override
    public boolean sendMoney(double amountOfMoney, long cardNumberSender, long cardNumberReceiver) {
        double currentBalance = 0;
        try {
            startTransaction();
            CreditCard creditCardSender = creditCardDAO.get(cardNumberSender);
            Bank bankAccountSender = bankDAO.get(cardNumberSender);
            Bank bankAccountReceiver = bankDAO.get(cardNumberReceiver);
            currentBalance = creditCardDAO.getBalance(cardNumberSender);
            if (amountOfMoney < currentBalance && !creditCardSender.getStatus().equals("DISABLED")) {
                bankAccountReceiver.setBalance(bankAccountReceiver.getBalance() + amountOfMoney);
                bankDAO.update(bankAccountReceiver);
                bankAccountSender.setBalance(bankAccountSender.getBalance() - amountOfMoney);
                bankDAO.update(bankAccountSender);
                transactionDAO.save(new Transaction(cardNumberSender, bankAccountSender.getAccountID(), "WITHDRAW", amountOfMoney));
                transactionDAO.save(new Transaction(cardNumberReceiver, bankAccountReceiver.getAccountID(), "DEPOSIT", amountOfMoney));
                commit();
                return true;
            }

        } catch (Throwable e) {
            rollback();
            logger.error("Error sending money,reason: "+e);
            throw new ServiceException("Error sending money,reason: "+e);

        }
        return false;
    }


    public static CreditCardService getInstance() {
        CreditCardService creditCardService = INSTANCE;
        if (creditCardService == null) {
            synchronized (CreditCardServiceImpl.class) {
                creditCardService = INSTANCE;
                if (creditCardService == null) {
                    INSTANCE = creditCardService = new CreditCardServiceImpl();
                }
            }
        }
        return creditCardService;
    }

}

