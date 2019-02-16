package services.Impl;

import dao.Impl.TransactionDAOImpl;
import dao.TransactionDAO;
import entities.Transaction;
import org.apache.log4j.Logger;
import services.ServiceException;
import services.TransactionService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class TransactionServiceImpl extends AbstractService implements TransactionService {
    private TransactionDAO transactionDAO = TransactionDAOImpl.getInstance();
    private static volatile TransactionService INSTANCE = null;
    public final static Logger logger = Logger.getLogger(BankServiceImpl.class);

    @Override
    public Transaction save(Transaction transaction) {
        try {
            startTransaction();
            transaction = transactionDAO.save(transaction);
            commit();
        } catch (SQLException e) {
            rollback();
            logger.error("Error saving new transaction " + transaction + e);
            throw new ServiceException("Error saving new transaction " + transaction + e);
        }
        return transaction;
    }

    @Override
    public Transaction get(Serializable transactionID) {
        try {
            return transactionDAO.get(transactionID);
        } catch (Throwable e) {
            logger.error("Error while getting info about transaction " + transactionID + " reason: " + e);
            throw new ServiceException("Error while getting info transaction " + transactionID + " reason: " + e);
        }
    }

    @Override
    public void update(Transaction transaction) {
        try {
            startTransaction();
            transactionDAO.update(transaction);
            commit();
        } catch (SQLException e) {
            rollback();
            logger.error("Error updating transaction " + transaction + " reason: " + e);
            throw new ServiceException("Error updating transaction " + transaction + " reason: " + e);
        }
    }

    @Override
    public int delete(Serializable transactionID) {
        try {
            return transactionDAO.delete(transactionID);
        } catch (SQLException e) {
            logger.error("Error deleting transaction " + transactionID + " reason: " + e);
            throw new ServiceException("Error deleting transaction " + transactionID + " reason: " + e);
        }
    }

    @Override
    public List<Transaction> getDayLimitWithdrawTransactions() {
        try {
            startTransaction();
            List<Transaction> list = transactionDAO.getDayLimitWithdrawTransaction();
            commit();
            return list;
        } catch (SQLException | NullPointerException e) {
            rollback();
            logger.error("Error while getting list of transactions " + " reason: " + e);
            throw new ServiceException("Error while getting list of transactions " + " reason: " + e);

        }
    }

    @Override
    public List<Transaction> getAllByClientID(Serializable clientID) {
        try {
            startTransaction();
            List<Transaction> list = transactionDAO.getAllByClientID(clientID);
            commit();
            return list;
        } catch (SQLException | NullPointerException e) {
            rollback();
            logger.error("Error while getting list of transactions " + " reason: " + e);
            throw new ServiceException("Error while getting list of transactions " + " reason: " + e);

        }
    }

    @Override
    public List<Transaction> getAll() {
        try {
            startTransaction();
            List<Transaction> list = transactionDAO.getAll();
            commit();
            return list;
        } catch (SQLException | NullPointerException e) {
            rollback();
            logger.error("Error while getting list of transactions " + " reason: " + e);
            throw new ServiceException("Error while getting list of transactions " + " reason: " + e);

        }
    }


    public static TransactionService getInstance() {
        TransactionService transactionService = INSTANCE;
        if (transactionService == null) {
            synchronized (TransactionServiceImpl.class) {
                if (transactionService == null) {
                    INSTANCE = transactionService = new TransactionServiceImpl();
                }
            }

        }
        return transactionService;
    }

}
