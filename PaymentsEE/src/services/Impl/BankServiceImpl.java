package services.Impl;

import dao.BankDAO;
import dao.Impl.BankDAOImpl;
import entities.Bank;
import org.apache.log4j.Logger;
import services.BankService;
import services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class BankServiceImpl extends AbstractService implements BankService {

    private BankDAO bankDAO = BankDAOImpl.getInstance();
    private static volatile BankService INSTANCE = null;
    public final static Logger logger = Logger.getLogger(BankServiceImpl.class);


    @Override
    public Bank save(Bank bank) {
        try {
            startTransaction();
            bank = bankDAO.save(bank);
            commit();
        } catch (SQLException e) {
            rollback();
            logger.error("Error saving new bank account " + bank + e);
            throw new ServiceException("Error saving new bank account " + bank + e);
        }
        return bank;
    }

    @Override
    public Bank get(Serializable cardNumber) {
        try {
            return bankDAO.get(cardNumber);
        } catch (Throwable e) {
            logger.error("Error while getting info about bank account " + cardNumber + " reason: " + e);
            throw new ServiceException("Error while getting info about bank account " + cardNumber + " reason: " + e);
        }
    }

    @Override
    public void update(Bank bank) {
        try {
            startTransaction();
            bankDAO.update(bank);
            commit();
        } catch (SQLException e) {
            rollback();
            logger.error("Error updating bank " + bank + " reason: " + e);
            throw new ServiceException("Error updating bank " + bank + " reason: " + e);
        }
    }

    @Override
    public int delete(Serializable accountID) {
        try {
            return bankDAO.delete(accountID);
        } catch (SQLException e) {
            logger.error("Error deleting bank " + accountID + " reason: " + e);
            throw new ServiceException("Error deleting bank " + accountID + " reason: " + e);
        }
    }

    @Override
    public List<Bank> getAll() {
        try {
            startTransaction();
            List<Bank> list = bankDAO.getAll();
            commit();
            return list;
        } catch (SQLException | NullPointerException e) {
            rollback();
            logger.error("Error while getting list of banks " + " reason: " + e);
            throw new ServiceException("Error while getting list of banks " + " reason: " + e);

        }
    }


    public static BankService getInstance() {
        BankService bankService = INSTANCE;
        if (bankService == null) {
            synchronized (BankServiceImpl.class) {
                if (bankService == null) {
                    INSTANCE = bankService = new BankServiceImpl();
                }
            }
        }
        return bankService;
    }
}
